package cn.edu.bnu.set.scorm.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.bnu.set.scorm.MainActivity;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Net.CourseListWorker;
import cn.edu.bnu.set.scorm.Util.PrePreference;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CourseGroupFragment extends ListFragment {
	public static final String ARG_COURSE_JSON = "course_json";
	private String thisJSON;
	ArrayList<JSONObject> courses = new ArrayList<JSONObject>();
	ArrayList<JSONObject> courseGroups = new ArrayList<JSONObject>();
	ArrayList<JSONObject> mItems = new ArrayList<JSONObject>();

	// ResourceDownloader<CheckBox> mResourceDownloader;

	private void initFragment() {
		try {
			JSONObject json = new JSONObject(thisJSON);
			if (json.has("course")) {
				JSONArray array = json.getJSONArray("course");
				for (int i = 0; i < array.length(); i++) {
					courses.add(array.getJSONObject(i));
				}
			}
			if (json.has("courseGroup")) {
				JSONArray array = json.getJSONArray("courseGroup");
				for (int i = 0; i < array.length(); i++) {
					courseGroups.add(array.getJSONObject(i));
				}
			}
			mItems.addAll(courses);
			mItems.addAll(courseGroups);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		thisJSON = getArguments().getString(ARG_COURSE_JSON);
		initFragment();
		setupListAdapter();
	}

	void setupListAdapter() {
		setListAdapter(new CourseAndGroupsAdapter(mItems));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list, null, false);

		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		JSONObject item = ((CourseAndGroupsAdapter) getListAdapter())
				.getItem(position);
		if (position < courses.size()) {
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
				getActivity().setTitle(item.getString("title"));
			} catch(JSONException e) {
				
			}
		} else {
			Bundle args = new Bundle();
			Fragment fragment = null;
			try {
				fragment = new CourseGroupFragment();
				int groupId = item.getInt("id");
				JSONObject sendJSON = new PrePreference(getActivity())
						.getGroupCourseList(groupId);
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
				getActivity().setTitle(item.getString("title"));
			} catch (JSONException e) {
				
			}
		}

	}

	private class CourseAndGroupsAdapter extends ArrayAdapter<JSONObject> {
		public CourseAndGroupsAdapter(ArrayList<JSONObject> items) {
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
				if (position < courses.size()) {
					textView.setText("【课程】" + item.getString("title"));
					new CourseListWorker(
							(MainActivity) getActivity(),
							MainActivity.MODULE_COURSE_TO_COURSE_UNIT,
							item.getInt("id")).execute();
				} else if (position >= courses.size()
						&& position < mItems.size()) {
					textView.setText("【课程组】" + item.getString("title"));
					new CourseListWorker(
							(MainActivity) getActivity(),
							MainActivity.MODULE_COURSE_GROUP_TO_COURSE,
							item.getInt("id")).execute();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return convertView;
		}
	}
}
