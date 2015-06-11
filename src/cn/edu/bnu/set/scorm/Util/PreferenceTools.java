package cn.edu.bnu.set.scorm.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferenceTools {
	private static final String TAG = "PreferenceTools";
	
	@SuppressWarnings("deprecation")
	public static String ReadFromfile(String fileName, Context context) {
		StringBuilder returnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
			fIn = context.getResources().getAssets()
					.open(fileName, Context.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				returnString.append(line).append("\n");
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return returnString.toString();
	}

	public static final String getSPrefString(Context ctx, String prefName,
			String name, String def) {
		SharedPreferences mine = ctx.getSharedPreferences(prefName,
				Activity.MODE_PRIVATE);
		String v = mine.getString(name, def);
		return v;
	}

	public static final boolean savePref(Context ctx, String prefName,
			String k, String v) {
		boolean ok = false;
		try {
			SharedPreferences mine = ctx.getSharedPreferences(prefName,
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor ed = mine.edit();
			// String name=
			ed.putString(k, v);
			ed.apply();
			ok = true;
		} catch (Exception ex) {
			Log.d(TAG, ex.getMessage());
		}
		return ok;
	}

	public static final boolean removePrefKey(Context ctx, String prefName,
			String k) {
		boolean ok = false;
		try {
			SharedPreferences mine = ctx.getSharedPreferences(prefName,
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor ed = mine.edit();
			// String name=
			ed.remove(k);
			ed.apply();
			ok = true;
		} catch (Exception ex) {
			Log.d(TAG, ex.getMessage());
		}
		return ok;
	}

	public static final boolean savePref(Context ctx, String prefName,
			String k, long v) {
		boolean ok = false;
		try {
			SharedPreferences mine = ctx.getSharedPreferences(prefName,
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor ed = mine.edit();
			// String name=
			ed.putLong(k, v);
			ed.apply();
			ok = true;
		} catch (Exception ex) {
			Log.d(TAG, ex.getMessage());
		}
		return ok;
	}
}
