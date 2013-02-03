package com.movember.treasureapp.activity;

import java.util.Locale;
import org.jsoup.Jsoup;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import com.example.treasureapp.R;
import com.movember.treasureapp.tool.ActivitySwitcher;

public class HtmlViewer extends Activity implements OnInitListener {
	private WebView web_view;
	private String pista, premio, felicitacion;
	private TextToSpeech tts;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.htmlviewer_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		this.web_view = (WebView) this.findViewById(R.id.webview);

		Bundle bundle = getIntent().getExtras();
		pista = bundle.getString("pista");
		premio = bundle.getString("premio");
		felicitacion = bundle.getString("felicitacion");
		String htmlPista = "<div style='text-align:center;margin-bottom: 10px;'><b>PISTA</b></div>" + pista;
		tts = new TextToSpeech(this, this);

		this.web_view.loadData(htmlPista, "text/html", "utf-8");

	}

	public void onClickContinue(View view) {
		String title = "<div style='text-align:center;margin-bottom: 10px;'><b>titulo</b></div>";
		if (premio.length() > 0) {
			String htmlPremio = title.replace("titulo", "PREMIO") + premio;
			web_view.loadData(htmlPremio, "text/html", "utf-8");
			speakOut(premio);
			premio = "";
		}
		else if (felicitacion.length() > 0) {
			String felicitacionHtml = title.replace("titulo", "FELICITACI&Oacute;N") + felicitacion;
			web_view.loadData(felicitacionHtml, "text/html", "utf-8");
			speakOut(felicitacion);
			felicitacion = "";
		}
		else {
			Intent intent = new Intent(getApplicationContext(), Main.class);
			animatedStartActivity(intent);
		}
	}

	@Override
	protected void onResume() {
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
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

	public void onInit(int status) {
		// TODO Auto-generated method stub
		// TTS is successfully initialized
		if (status == TextToSpeech.SUCCESS) {
			// Setting speech language
			int result = tts.setLanguage(new Locale("es", "ES"));
			// If your device doesn't support language you set above
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Cook simple toast message with message
				Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
				Log.e("TTS", "Language is not supported");
			}
			else {
				speakOut(this.pista);
			}
		}
		else {
			Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
			Log.e("TTS", "Initilization Failed");
		}
	}

	private void speakOut(String htmlMessage) {
		String message = Jsoup.parse(htmlMessage).text();
		if (message.length() == 0) {
			tts.speak("No hay mensaje", TextToSpeech.QUEUE_FLUSH, null);
		}
		else {
			tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
		}

	}
}
