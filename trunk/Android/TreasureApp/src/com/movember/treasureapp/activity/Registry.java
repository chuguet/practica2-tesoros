package com.movember.treasureapp.activity;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.treasureapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.movember.treasureapp.tool.ActivitySwitcher;
import com.movember.treasureapp.tool.PropertiesService;
import com.movember.treasureapp.tool.UserInformationService;

public class Registry extends Activity {
	private EditText nombre, apellidos, email, usuario, pwd;
	// private Button btnRegistrar, btnCancelar;
	// private Intent mainActivity;
	private Properties prop;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registry_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		this.prop = PropertiesService.loadProperties(this);

		this.nombre = (EditText) findViewById(R.id.et_nombre);
		this.apellidos = (EditText) findViewById(R.id.et_apellidos);
		this.email = (EditText) findViewById(R.id.et_email);
		this.usuario = (EditText) findViewById(R.id.et_usuario);
		this.pwd = (EditText) findViewById(R.id.et_pw);

		// this.btnRegistrar = (Button) findViewById(R.id.btn_accept);
		// this.btnCancelar = (Button) findViewById(R.id.btn_cancel);

		// this.btnRegistrar.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// }
		// });

		// this.btnCancelar.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// }
		// });
	}

	@Override
	protected void onResume() {
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
	}

	public void onClickAccept(View view) {
		AsyncHttpClient client = new AsyncHttpClient();
		JSONObject jsonObject = new JSONObject();
		StringEntity entity = null;

		if (validateParameters()) {
			try {
				String uuid = UserInformationService.readUuid(getApplicationContext());
				jsonObject.put("nombre", nombre.getText().toString());
				jsonObject.put("apellidos", apellidos.getText().toString());
				jsonObject.put("email", email.getText().toString());
				jsonObject.put("usuario", usuario.getText().toString());
				jsonObject.put("pwd", pwd.getText().toString());
				jsonObject.put("uuid", uuid);

				entity = new StringEntity(jsonObject.toString());

				String contentType = "application/json; charset=UTF-8";

				String restApiUri = prop.getProperty("serverUri") + "registroUsuario";
				client.post(getApplicationContext(), restApiUri, entity, contentType, new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject jObject) {
						try {
							if (!jObject.getBoolean("correcto")) {
								showMessage("Error de registro", jObject.getString("mensaje"));
							}
							else {
								UserInformationService.writeName(getApplicationContext(), nombre.getText().toString() + " " + apellidos.getText().toString());
								accessApplication("Se ha registrado correctamente en el sistema", "Registro correcto");
							}
						}
						catch (JSONException e) {
							showMessage("Error de registro", e.getMessage());
						}
					}

					@Override
					public void onFailure(Throwable arg0) {
						showMessage("Error de registro", "Se ha producido un error en el registro. Vuelva a intentarlo");
					}
				});
			}
			catch (JSONException e) {
				showMessage("Error de registro", e.getMessage());
			}
			catch (UnsupportedEncodingException e) {
				showMessage("Error de registro", e.getMessage());
			}
		}
	}

	public void onClickCancel(View view) {
		animatedStartActivity(new Intent(this, Login.class));
	}

	private boolean validateParameters() {
		String errors = "";
		if (this.nombre.length() == 0) {
			errors += " - El nombre es obligatorio.\n";
		}
		if (this.apellidos.length() == 0) {
			errors += " - Los apellidos son obligatorios.\n";
		}
		if (this.email.length() == 0) {
			errors += " - El email es obligatorio.\n";
		}
		if (this.usuario.length() == 0) {
			errors += " - El usuario es obligatorio.\n";
		}
		if (this.pwd.length() == 0) {
			errors += " - La contraseña es obligatoria.\n";
		}
		if (errors.length() > 0) {
			errors = "Se han producido los siguientes errores:\n" + errors;
			showMessage("Error de registro", errors);
			return false;
		}
		return true;
	}

	private void accessApplication(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(getApplicationContext(), Main.class);
				intent.putExtra("user", nombre.getText().toString() + " " + apellidos.getText().toString());
				animatedStartActivity(intent);
			}
		});

		alertDialog.show();
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
