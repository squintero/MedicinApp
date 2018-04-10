package com.squintero.medicinapp.utilities;

import android.util.Log;

import com.squintero.medicinapp.BuildConfig;


public final class Logs {
    
	//Show system logs on the screen or hide it.
	public static void SystemLog(String texto){

		if (BuildConfig.SHOW_LOGS){	//Show log
			System.out.println(texto);
		}
	}
	
	//Show the different validField type logs on the screen or hide it.
	public static void MessageLogs(String tag, String texto, String tipo){

		if (BuildConfig.SHOW_LOGS && tipo.equals("i")){			// Info
			Log.i(tag, texto);
		}else if (BuildConfig.SHOW_LOGS && tipo.equals("v")){	// Verbose
			Log.v(tag, texto);
		}else if (BuildConfig.SHOW_LOGS && tipo.equals("w")){	//Warning
			Log.w(tag, texto);
		}else if (BuildConfig.SHOW_LOGS && tipo.equals("e")){	// Error
			Log.e(tag, texto);
		}else if (BuildConfig.SHOW_LOGS && tipo.equals("wtf")){	// What Terrible Failure
			Log.wtf(tag, texto);
		}
	}
}
