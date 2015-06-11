package cn.edu.bnu.set.scorm.Net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONResourceFetchr {

	public ArrayList<JSONArray> fetchItems(String json) {
		ArrayList<JSONArray> items = new ArrayList<JSONArray>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray courseUnitArray = null;
			JSONArray courseUnitGroupArray = null;
			if (jsonObject.has("courseUnit")) {
				courseUnitArray = jsonObject.getJSONArray("courseUnit");
				items.add(courseUnitArray);
			}
			if (jsonObject.has("courseUnitGroup")) {
				courseUnitGroupArray = jsonObject
						.getJSONArray("courseUnitGroup");
				items.add(courseUnitGroupArray);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return items;
	}

}
