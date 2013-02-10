package com.movember.treasureapp.activity;

import java.util.Properties;
import java.util.UUID;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewFlipper;
import com.example.treasureapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.movember.treasureapp.tool.ActivitySwitcher;
import com.movember.treasureapp.tool.PropertiesService;
import com.movember.treasureapp.tool.UserInformationService;

public class Login extends Activity {
	private EditText un, pw;
	private Properties prop;
	ViewFlipper flipper;
	private Integer intentos = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.prop = PropertiesService.loadProperties(this);
		verifyDeviceRegistered();
		verifyUserLogged();

		this.un = (EditText) findViewById(R.id.et_un);
		this.pw = (EditText) findViewById(R.id.et_pw);
	}

	public void onClickLogin(View view) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams rp = new RequestParams();
		rp.put("j_username", un.getText().toString());
		rp.put("j_password", pw.getText().toString());

		client.post(prop.getProperty("serverUri") + "j_spring_security_check", rp, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject jObject) {
				String user = "";
				try {
					if (jObject.has("error")) {
						showMessage("Error de acceso", "Usuario y/o contraseña incorrectos. Vuelva a intentarlo");
					}
					else {
						if (jObject.getString("nombre") != null && jObject.getString("nombre").length() > 0) {
							user = jObject.getString("nombre") + " ";
						}
						if (jObject.getString("apellidos") != null && jObject.getString("apellidos").length() > 0) {
							user += jObject.getString("apellidos");
						}
						UserInformationService.writeName(getApplicationContext(), user);
						accessApplication();
					}
				}
				catch (JSONException e) {
					Log.d("tag", "Fallo al identificar el usuario");
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				showMessage("Error de acceso", "Se ha producido un error de acceso. Vuelva a intentarlo.");
			}
		});
	}

	@Override
	protected void onResume() {
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
	}

	public void onClickAnonimous(View view) {
		accessApplication();
	}

	public void onClickRegister(View view) {
		this.animatedStartActivity(new Intent(getApplicationContext(), Registry.class));
	}

	private void verifyDeviceRegistered() {
		String uuid = UserInformationService.readUuid(this);
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("uuid", uuid);
				AsyncHttpClient client = new AsyncHttpClient();
				StringEntity entity = new StringEntity(jsonObject.toString());

				String contentType = "application/json; charset=UTF-8";

				String restApiUri = prop.getProperty("serverUri") + "registroDispositivo";
				client.post(getApplicationContext(), restApiUri, entity, contentType, new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject jObject) {
						try {
							if (jObject.has("correcto") && jObject.get("correcto").toString().equals("false")) {
								verifySynchronizingError();

							}
							else {
								UserInformationService.writeUuid(getApplicationContext(), jObject.getString("parameter"));
							}
						}
						catch (JSONException e) {
							Log.d("tag", "Fallo al registrar el dispositivo");
							verifySynchronizingError();
						}
					}

					@Override
					public void onFailure(Throwable arg0) {
						verifySynchronizingError();
					}
				});
			}
			catch (Exception e) {
				Log.d("tag", "Fallo al registrar el dispositivo");
				verifySynchronizingError();
			}
		}
	}

	private void verifySynchronizingError() {
		this.intentos++;
		if (this.intentos > 5) {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Error de sincronización");
			alertDialog.setMessage("Hay problemas de sincronización con el servidor. Vuelva a intentarlo más tarde");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					killApp();
				}
			});
			alertDialog.show();
		}
		else {
			verifyDeviceRegistered();
		}
	}

	private void killApp() {
		finish();
		System.exit(0);
	}

	private void accessApplication() {
		this.animatedStartActivity(new Intent(getApplicationContext(), Main.class));
	}

	private void verifyUserLogged() {
		String name = UserInformationService.readName(this);
		if (name != null && name.length() > 0) {
			Intent intent = new Intent(getApplicationContext(), Main.class);
			startActivity(intent);
		}
	}

	private void showMessage(String title, String message) {
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

	private void animatedStartActivity(final Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
	}
}
