package com.movember.treasureapp.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class AlertService {
	public static AlertDialog showMessage(Context context, String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		return alertDialog;
	}
}
