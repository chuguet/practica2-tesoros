package com.movember.treasureapp.tool;

import com.movember.treasureapp.activity.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class UserInformationService {
	public static String readName(Context context){
		return read(context, "name");
	}
	
	public static String readUuid(Context context){
		return read(context, "uuid");
	}
	
	public static void writeName(Context context, String name){
		write(context, "name", name);
	}
	
	public static void writeUuid(Context context, String uuid){
		write(context, "uuid", uuid);
	}
	
	private static String read(Context context, String key){
		SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
		return settings.getString(key, null);
	}
	
	private static void write(Context context, String key, String value){
		SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void clear(Context context){
		SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("name", null);
		editor.putString("uuid", null);
		editor.commit();
	}
}
