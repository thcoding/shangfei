package cn.edu.bnu.set.scorm.Util;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.bnu.set.scorm.Datas.User;
import android.content.Context;
import android.util.Log;

public class PrePreference {
	// 文件名
	public static final String PREF_NAME = "SCORM_PREF_ALL";
	// key
	public static final String PREF_USER = "SCORM_USER";
	public static final String PREF_COURSES = "SCORM_COURSES";
	public static final String PREF_COURSE_UNITS = "SCORM_COURSE_UNITS";
	public static final String URL_API = "URL_API";

	private Context context;

	public PrePreference(Context context) {
		super();
		this.context = context;
	}

	public void save(String prefName, String k, String v) {
		// 三个参数分别为：文件名、key、value
		PreferenceTools.savePref(context, prefName, k, v);
	}

	public void save(String k, String v) {
		save(PREF_NAME, k, v);
	}

	public String getString(String prefName, String k, String def) {
		String v = def;
		v = PreferenceTools.getSPrefString(context, prefName, k, def);
		return v;
	}

	public String getString(String k, String def) {
		return getString(PREF_NAME, k, def);
	}

	public void saveUser(User u) {
		String jsonString;
		try {
			jsonString = u.toJSON().toString();
			save(PREF_NAME, PREF_USER, jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void removeUser() {
		PreferenceTools.removePrefKey(context, PREF_NAME, PREF_USER);
	}

	public User getUser() {
		User u = null;
		String jsonString = getString(PREF_USER, null);
		if (jsonString != null) {
			try {
				u = new User(new JSONObject(jsonString));
			} catch (Exception ex) {
				u = null;
				Log.d("pref-get-user", ex.getMessage() + " " + jsonString);
			}
		} else {
			return null;
		}
		return u;
	}

	public void saveUserCourseList(JSONObject courseList) {
		save(PREF_NAME, PREF_COURSES, courseList.toString());
	}

	public void saveGroupCourseList(JSONObject courseList, int groupId) {
		save(PREF_NAME, PREF_COURSES + groupId, courseList.toString());
	}

	public JSONObject getUserCourseList() {
		JSONObject list = null;
		String jsonString = getString(PREF_COURSES, null);
		if (jsonString == null) {
			return null;
		}
		try {
			list = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.d("pref-get-course", e.getMessage() + " " + jsonString);
		}
		return list;
	}

	public void removeUserCourseList() {
		PreferenceTools.removePrefKey(context, PREF_NAME, PREF_COURSES);
	}

	public void saveCourseUnitList(JSONObject courseUnitList, int courseId) {
		save(PREF_NAME, PREF_COURSE_UNITS + courseId, courseUnitList.toString());
	}

	public JSONObject getCourseUnitList(int courseId) {
		JSONObject list = null;
		String jsonString = getString(PREF_COURSE_UNITS + courseId, null);
		if (jsonString == null) {
			return null;
		}
		try {
			list = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.d("pref-get-course", e.getMessage() + " " + jsonString);
		}
		return list;
	}

	public JSONObject getGroupCourseList(int groupId) {
		JSONObject list = null;
		String jsonString = getString(PREF_COURSES + groupId, null);
		if (jsonString == null) {
			return null;
		}
		try {
			list = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.d("pref-get-course", e.getMessage() + " " + jsonString);
		}
		return list;
	}

}
