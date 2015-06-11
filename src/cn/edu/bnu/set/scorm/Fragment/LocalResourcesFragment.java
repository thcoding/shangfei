package cn.edu.bnu.set.scorm.Fragment;

import java.io.*;
import java.util.*;

import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Datas.LearningResource;
import cn.edu.bnu.set.scorm.Util.OpenFiles;
import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class LocalResourcesFragment extends ListFragment {
	ArrayList<LearningResource> mItems = new ArrayList<LearningResource>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		File fileRoot = getActivity().getFilesDir();
		File[] files = fileRoot.listFiles();
		for (File f : files) {
			mItems.add(new LearningResource(f.getName(), null));
		}

		setListAdapter(new ArrayAdapter<LearningResource>(getActivity(), 0,
				mItems) {
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getActivity().getLayoutInflater().inflate(
							R.layout.resource_list_item, parent, false);
				}
				LearningResource item = getItem(position);
				TextView textView = (TextView) convertView
						.findViewById(R.id.resource_name);
				textView.setText(item.getTitle());
				CheckBox checkBox = (CheckBox) convertView
						.findViewById(R.id.resource_downloadedCheckBox);
				checkBox.setChecked(true);
				return convertView;
			}
		});
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		LearningResource item = (LearningResource) getListAdapter().getItem(
				position);
		String itemName = item.getTitle();
		File currentPath = new File(getActivity().getFilesDir() + "/"
				+ itemName);

		if (currentPath != null && currentPath.isFile()) {
			String fileName = currentPath.toString();
			Intent intent;
			if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingImage))) {
				intent = OpenFiles.getImageFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingWebText))) {
				intent = OpenFiles.getHtmlFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingPackage))) {
				intent = OpenFiles.getApkFileIntent(currentPath);
				startActivity(intent);

			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingAudio))) {
				intent = OpenFiles.getAudioFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingVideo))) {
				intent = OpenFiles.getVideoFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingText))) {
				intent = OpenFiles.getTextFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingPdf))) {
				intent = OpenFiles.getPdfFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingWord))) {
				intent = OpenFiles.getWordFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingExcel))) {
				intent = OpenFiles.getExcelFileIntent(currentPath);
				startActivity(intent);
			} else if (checkEndsWithInStringArray(fileName, getResources()
					.getStringArray(R.array.fileEndingPPT))) {
				intent = OpenFiles.getPPTFileIntent(currentPath);
				startActivity(intent);
			} else {
				Toast.makeText(getActivity(), "无法打开，请安装相应的软件！",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(getActivity(), "不是文件！", Toast.LENGTH_LONG).show();
		}

	}

	private boolean checkEndsWithInStringArray(String checkItsEnd,
			String[] fileEndings) {
		for (String aEnd : fileEndings) {
			if (checkItsEnd.endsWith(aEnd))
				return true;
		}
		return false;
	}
}
