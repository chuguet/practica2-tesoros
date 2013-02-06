package com.movember.treasureapp.activity;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.treasureapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.movember.treasureapp.tool.ActivitySwitcher;
import com.movember.treasureapp.tool.PropertiesService;
import com.movember.treasureapp.tool.UserInformationService;

public class Main extends Activity implements Camera.PreviewCallback, ZBarConstants {
	private Properties prop;
	// private static final int CAMERA_REQUEST = 1888;
	private ImageView imageView;
	private Button btn_Enviar;
	private TextView tv_codigoQR, tv_longitude, tv_latitude, tv_user;

	private Bitmap bmp;
	private String text;
	private String longitude;
	private String latitude;

	/* GPS */
	/** location members **/
	private static LocationManager lm;
	private static LocationListener locationListener;
	private static Location currentLocation = null;

	/** constants **/
	private final static String TAG_FINDER = "LocationFinder";
	// check every minutes
	private final static int LOCATION_UPDATE_TIME = 60000;
	// don't bother if its not more than 100M
	private final static int LOCATION_UPDATE_DISTANCE = 100;

	private static final String TAG = "ZBarScannerActivity";
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private CameraPreview mPreview;
	private Camera mCamera;
	private ImageScanner mScanner;
	private Handler mAutoFocusHandler;
	private boolean mPreviewing = true;

	static {
		System.loadLibrary("iconv");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		this.prop = PropertiesService.loadProperties(this);

		this.tv_user = (TextView) this.findViewById(R.id.tv_user);
		this.tv_codigoQR = (TextView) this.findViewById(R.id.tv_codigoQR);
		this.tv_longitude = (TextView) this.findViewById(R.id.tv_longitude);
		this.tv_latitude = (TextView) this.findViewById(R.id.tv_latitude);
		this.btn_Enviar = (Button) this.findViewById(R.id.btn_send);
		startGPS();
		showUserName();
	}

	@Override
	protected void onResume() {
		super.onResume();
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
	}

	@Override
	protected void onPause() {
		super.onPause();

		disconnectCamera();
	}

	public void onLaunchQRScanner(View v) {
		disconnectCamera();
		if (isCameraAvailable()) {
			prepareScanner();
		}
		else {
			Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
		}
	}

	public void onEnviar(View v) {
		AsyncHttpClient client = new AsyncHttpClient();
		JSONObject jsonObject = new JSONObject();
		StringEntity entity = null;

		if (validateParameters()) {
			try {
				jsonObject.put("codigo", this.text);
				jsonObject.put("longitud", this.longitude);
				jsonObject.put("latitud", this.latitude);
				jsonObject.put("uuid", UserInformationService.readUuid(getApplicationContext()));

				entity = new StringEntity(jsonObject.toString());

				String contentType = "application/json; charset=UTF-8";

				String restApiUri = prop.getProperty("serverUri") + "registroHito";
				client.post(getApplicationContext(), restApiUri, entity, contentType, new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject jObject) {
						try {
							if (!jObject.getBoolean("correcto")) {
								showMessage("Error de checkin", jObject.getString("mensaje"));
							}
							else {
								checkinCorrecto(jObject.get("parameter"));
							}
						}
						catch (JSONException e) {
							showMessage("Error de checkin", e.getMessage());
						}
					}

					@Override
					public void onFailure(Throwable arg0) {
						showMessage("Error de checkin", "Se ha producido un error en el registro. Vuelva a intentarlo");
					}
				});
			}
			catch (JSONException e) {
				showMessage("Error de checkin", e.getMessage());
			}
			catch (UnsupportedEncodingException e) {
				showMessage("Error de checkin", e.getMessage());
			}
		}
	}

	public void onLogout(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		alertDialogBuilder.setMessage("¿Está seguro que quiere cerrar la sesión actual?").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Context context = getApplicationContext();
				UserInformationService.clear(context);
				Intent intent = new Intent(context, Login.class);
				animatedStartActivity(intent);
			}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	private boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	private void showUserName() {
		String user = UserInformationService.readName(this);
		if (user == null || user.length() == 0) {
			user = "Anónimo";
		}
		this.tv_user.setText("Usuario: " + user);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable("bmp", this.bmp);
		outState.putString("text", this.text);
		outState.putString("longitude", this.longitude);
		outState.putString("latitude", this.latitude);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		this.bmp = savedInstanceState.getParcelable("bmp");
		this.text = savedInstanceState.getString("text");
		this.longitude = savedInstanceState.getString("longitude");
		this.latitude = savedInstanceState.getString("latitude");
		this.repaintOnChange();
		super.onRestoreInstanceState(savedInstanceState);
	}

	private void repaintOnChange() {
		if (this.bmp != null) {
			this.showImage();
		}
		if (this.text != null) {
			this.tv_codigoQR.setText(this.text);
			this.setEnableEnviar(true);
		}
		if (this.longitude != null) {
			this.tv_longitude.setText(this.longitude);
		}
		if (this.latitude != null) {
			this.tv_latitude.setText(this.latitude);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			this.bmp = (Bitmap) data.getExtras().get("data");
			this.text = data.getStringExtra(ZBarConstants.SCAN_RESULT);

			if (this.text != null) {
				Toast.makeText(this, "Scan Result = " + this.text, Toast.LENGTH_SHORT).show();
				tv_codigoQR.setText(this.text);
				this.showImage();
				this.setEnableEnviar(true);
			}
			else {

				Toast.makeText(this, "Error al procesar el QR. Vuelva a capturar la imagen", Toast.LENGTH_LONG).show();
			}
		}
	}

	private void showImage() {
		if (this.imageView == null) {
			// this.imageView = (ImageView) this.findViewById(R.id.imageView1);

			this.imageView = new ImageView(getApplicationContext());
			this.imageView.setScaleType(ImageView.ScaleType.CENTER);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.scanner_layout);
			rl.addView(imageView, lp);

		}
		// set your image on imageview
		imageView.setImageBitmap(this.bmp);
	}

	public void startGPS() {

		// ---use the LocationManager class to obtain GPS locations---
		if (lm == null) {
			lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		}

		locationListener = new MyLocationListener();
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) && !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			showGPSDisabledAlertToUser();
		}
		else {
			if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

				Log.i(TAG_FINDER, "starting up GPS location provider...");

				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_TIME, LOCATION_UPDATE_DISTANCE, locationListener);

				currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				updateLocation(currentLocation);
			}

			if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				Log.i(TAG_FINDER, "starting up Network location provider...");

				lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_TIME, LOCATION_UPDATE_DISTANCE, locationListener);

				currentLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				updateLocation(currentLocation);
			}
		}
	}

	private void updateLocation(Location loc) {
		try {
			if (loc != null) {
				String locText = "You are at: Lat:" + (loc.getLatitude()) + " Lon:" + (loc.getLongitude());
				Log.i(TAG_FINDER, locText);

				this.longitude = loc.getLongitude() + "";
				this.latitude = loc.getLatitude() + "";
				tv_longitude.setText(this.longitude);
				tv_latitude.setText(this.latitude);
			}
		}
		catch (Exception e) {
			Log.d(TAG_FINDER, "Error update location: " + e.getMessage());
		}
	}

	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {
			if (loc != null) {
				updateLocation(loc);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.i(TAG_FINDER, "provider disabled: " + provider);
		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.i(TAG_FINDER, "provider enabled: " + provider);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.i(TAG_FINDER, "status changed: " + provider + "=" + status);
		}

		public void updateStatus() {
			Log.i(TAG_FINDER, "status updated");
		}
	}

	private void showGPSDisabledAlertToUser() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("GPS y WIFI están deshabilitados en tu dispositivo. ¿Te gustaría habilitarlos?").setCancelable(false).setPositiveButton("Activar GPS/WIFI", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				animatedStartActivity(callGPSSettingIntent);
			}
		});
		alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	private void setEnableEnviar(boolean enable) {
		this.btn_Enviar.setEnabled(enable);
	}

	private boolean validateParameters() {
		String errors = "";
		if (this.text.length() == 0) {
			errors += " - No ha capturado ningún código QR obligatorio.\n";
		}
		if (this.longitude.length() == 0 || this.latitude.length() == 0) {
			errors += " - Debe estar geoposicionado.\n";
		}
		if (errors.length() > 0) {
			errors = "Se han producido los siguientes errores:\n" + errors;
			showMessage("Error de registro", errors);
			return false;
		}
		return true;
	}

	public void showMessage(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();
	}

	private void checkinCorrecto(final Object object) throws JSONException {

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Checkin correcto");
		alertDialog.setMessage("El checkeo del hito se ha realizado correctamente");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					dialog.dismiss();
					JSONArray mensajes = (JSONArray) object;
					String pista = mensajes.getString(0);
					String premio = mensajes.getString(1);
					String felicitacion = mensajes.getString(2);
					Intent intent = new Intent(getApplicationContext(), HtmlViewer.class);
					intent.putExtra("pista", pista);
					intent.putExtra("premio", premio);
					intent.putExtra("felicitacion", felicitacion);
					disconnectCamera();
					animatedStartActivity(intent);
				}
				catch (JSONException e) {
					showMessage("Error de checkin", "Se ha producido un error en el registro. Vuelva a intentarlo");
				}
			}
		});
		alertDialog.show();
	}

	private void animatedStartActivity(final Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
	}

	private void prepareScanner() {
		mAutoFocusHandler = new Handler();

		// Create and configure the ImageScanner;
		mScanner = new ImageScanner();
		mScanner.setConfig(0, Config.X_DENSITY, 3);
		mScanner.setConfig(0, Config.Y_DENSITY, 3);

		int[] symbols = getIntent().getIntArrayExtra(SCAN_MODES);
		if (symbols != null) {
			mScanner.setConfig(Symbol.NONE, Config.ENABLE, 0);
			for (int symbol : symbols) {
				mScanner.setConfig(symbol, Config.ENABLE, 1);
			}
		}

		// Create a RelativeLayout container that will hold a SurfaceView,
		// and set it as the content of our activity.
		mPreview = new CameraPreview(this, this, autoFocusCB);
		mCamera = Camera.open();
		mCamera.setDisplayOrientation(90);
		mPreviewing = true;
		mPreview.setCamera(mCamera);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.scanner_layout);
		rl.addView(mPreview, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void onPreviewFrame(byte[] data, Camera camera) {
		Camera.Parameters parameters = camera.getParameters();
		Camera.Size size = parameters.getPreviewSize();

		Image barcode = new Image(size.width, size.height, "Y800");
		barcode.setData(data);

		int result = mScanner.scanImage(barcode);

		if (result != 0) {
			mCamera.cancelAutoFocus();
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mPreviewing = false;
			SymbolSet syms = mScanner.getResults();
			for (Symbol sym : syms) {
				this.text = sym.getData();
				if (!TextUtils.isEmpty(this.text)) {
					Toast.makeText(this, "Scan Result = " + this.text, Toast.LENGTH_SHORT).show();
					tv_codigoQR.setText(this.text);
					this.setEnableEnviar(true);
					break;
				}
			}
		}
	}

	private Runnable doAutoFocus = new Runnable() {
		public void run() {
			if (mCamera != null && mPreviewing) {
				mCamera.autoFocus(autoFocusCB);
			}
		}
	};

	// Mimic continuous auto-focusing
	Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			mAutoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};

	private void disconnectCamera() {
		// Because the Camera object is a shared resource, it's very
		// important to release it when the activity is paused.
		if (mCamera != null) {
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.scanner_layout);
			rl.removeAllViews();
			mPreview.setCamera(null);
			mCamera.cancelAutoFocus();
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mPreviewing = false;
			mCamera = null;
		}
	}
}