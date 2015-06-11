package cn.edu.bnu.set.scorm.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.bnu.set.scorm.MainActivity;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.WebViewActivity;
import cn.edu.bnu.set.scorm.Net.CourseListWorker;
import cn.edu.bnu.set.scorm.Util.PrePreference;
import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class CourseFragment extends ListFragment {
	public static final String ARG_COURSE_JSON = "course_json";
	private String thisJSON;
	ArrayList<JSONObject> courseUnits = new ArrayList<JSONObject>();
	ArrayList<JSONObject> courseUnitGroups = new ArrayList<JSONObject>();
	ArrayList<JSONObject> mItems = new ArrayList<JSONObject>();

	// ResourceDownloader<CheckBox> mResourceDownloader;

	private void initFragment() {
		try {
			JSONObject json = new JSONObject(thisJSON);
			if (json.has("courseUnit")) {
				JSONArray array = json.getJSONArray("courseUnit");
				for (int i = 0; i < array.length(); i++) {
					courseUnits.add(array.getJSONObject(i));
				}
			}
			if (json.has("courseUnitGroup")) {
				JSONArray array = json.getJSONArray("courseUnitGroup");
				for (int i = 0; i < array.length(); i++) {
					courseUnitGroups.add(array.getJSONObject(i));
				}
			}
			mItems.addAll(courseUnits);
			mItems.addAll(courseUnitGroups);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		thisJSON = getArguments().getString(ARG_COURSE_JSON);
		initFragment();
		// new FetchItemsTask().execute();

		/*
		 * mResourceDownloader = new ResourceDownloader<CheckBox>(new Handler(),
		 * getActivity()); mResourceDownloader .setListener(new
		 * ResourceDownloader.Listener<CheckBox>() {
		 * 
		 * @Override public void onResourceDownloaded(CheckBox checkBox, boolean
		 * isDownloaded) { if (isVisible()) { checkBox.setChecked(isDownloaded);
		 * } } }); mResourceDownloader.start(); mResourceDownloader.getLooper();
		 * Log.i(TAG, "Background thread started");
		 */
		setupListAdapter();
	}

	void setupListAdapter() {
		setListAdapter(new UnitAndGroupsAdapter(mItems));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list, null, false);

		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		JSONObject item = ((UnitAndGroupsAdapter) getListAdapter())
				.getItem(position);
		if (position < courseUnits.size()) {
			String itemUrl = null;
			try {
				itemUrl = item.getString("url");
			} catch (JSONException e) {
				Toast.makeText(getActivity(), "连接错误!\n请检查连接或联系管理员", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				return;
			}
			Intent i = new Intent(getActivity(),WebViewActivity.class);
			i.putExtra("URL", itemUrl);
			startActivity(i);
			/*
			 * File currentPath = new File(getActivity().getFilesDir() + "/" +
			 * itemName + ".html");
			 * 
			 * if (currentPath != null && currentPath.isFile()) { String
			 * fileName = currentPath.toString(); Intent intent; if
			 * (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingImage))) { intent =
			 * OpenFiles.getImageFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingWebText))) { intent =
			 * OpenFiles.getHtmlFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingPackage))) { intent =
			 * OpenFiles.getApkFileIntent(currentPath); startActivity(intent); }
			 * else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingAudio))) { intent =
			 * OpenFiles.getAudioFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingVideo))) { intent =
			 * OpenFiles.getVideoFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingText))) { intent =
			 * OpenFiles.getTextFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingPdf))) { intent =
			 * OpenFiles.getPdfFileIntent(currentPath); startActivity(intent); }
			 * else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingWord))) { intent =
			 * OpenFiles.getWordFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingExcel))) { intent =
			 * OpenFiles.getExcelFileIntent(currentPath); startActivity(intent);
			 * } else if (checkEndsWithInStringArray(fileName, getResources()
			 * .getStringArray(R.array.fileEndingPPT))) { intent =
			 * OpenFiles.getPPTFileIntent(currentPath); startActivity(intent); }
			 * else { Log.i(TAG, "无法打开，请安装相应的软件！"); } } else { Log.i(TAG,
			 * "不是文件"); }
			 */
		} else {
			Bundle args = new Bundle();
			Fragment fragment = null;
			try {
				fragment = new CourseFragment();
				int groupId = item.getInt("id");
				JSONObject sendJSON = new PrePreference(getActivity())
						.getCourseUnitList(groupId);
				if(sendJSON==null) {
					Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
					return;
				}
				args.putString(CourseFragment.ARG_COURSE_JSON,
						sendJSON.toString());
				fragment.setArguments(args);
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
				// update selected item and title, then close the drawer
				getActivity().setTitle(item.getString("title"));
			} catch (JSONException e) {

			}
		}

	}

	/*
	 * private boolean checkEndsWithInStringArray(String checkItsEnd, String[]
	 * fileEndings) { for (String aEnd : fileEndings) { if
	 * (checkItsEnd.endsWith(aEnd)) return true; } return false; }
	 */

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// mResourceDownloader.clearQueue();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// mResourceDownloader.quit();
		// Log.i(TAG, "Background thread destroyed");
	}

	/*
	 * private class FetchItemsTask extends AsyncTask<Void, Void, JSONObject> {
	 * 
	 * @Override protected JSONObject doInBackground(Void... params) { try {
	 * return new JSONObject(thisJSON); } catch (JSONException e) {
	 * e.printStackTrace(); } return new
	 * JSONResourceFetchr().fetchItems(courseJSON); }
	 * 
	 * @Override protected void onPostExecute(JSONObject items) { mItems =
	 * items; for (int i = 0; i < mItems.size(); i++) { Log.i(TAG,
	 * mItems.get(i).toString()); } setupListAdapter(); }
	 * 
	 * }
	 */

	private class UnitAndGroupsAdapter extends ArrayAdapter<JSONObject> {
		public UnitAndGroupsAdapter(ArrayList<JSONObject> items) {
			super(getActivity(), 0, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.resource_list_item, parent, false);
			}
			JSONObject item = getItem(position);
			TextView textView = (TextView) convertView
					.findViewById(R.id.resource_name);
			try {
				if (position < courseUnits.size()) {
					textView.setText("【单元】" + item.getString("title"));
				} else if (position >= courseUnits.size()
						&& position < mItems.size()) {
					textView.setText("【单元组】" + item.getString("title"));
					new CourseListWorker(
							(MainActivity) getActivity(),
							MainActivity.MODULE_COURSE_UNIT_GROUP_TO_COURSE_UNIT,
							item.getInt("id")).execute();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// CheckBox checkBox = (CheckBox) convertView
			// .findViewById(R.id.resource_downloadedCheckBox);
			// checkBox.setChecked(false);

			// mResourceDownloader.queueResource(checkBox, item.toString());

			return convertView;
		}
	}
}
