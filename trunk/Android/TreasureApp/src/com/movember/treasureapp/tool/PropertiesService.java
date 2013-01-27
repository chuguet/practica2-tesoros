package com.movember.treasureapp.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.util.Log;

public class PropertiesService {
	public static Properties loadProperties(Context context) {
		String[] fileList = { "app.properties" };
		Properties prop = new Properties();
		for (int i = fileList.length - 1; i >= 0; i--) {
			String file = fileList[i];
			try {
		        InputStream fileStream = context.getAssets().open(file);
		        prop.load(fileStream);
		        fileStream.close();
			} 
			catch (FileNotFoundException e) {
		        Log.d("tag", "Ignoring missing property file " + file);
			}
			catch (IOException e) {
				Log.d("tag", "Fallo al leer el fichero de properties");
			}
		}
		return prop;
	}
}
