package cn.edu.bnu.set.scorm.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cn.edu.bnu.set.scorm.LoginActivity;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Util.PrePreference;

public class UserFragment extends Fragment {

	public UserFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_user, container,
				false);
		TextView userInfo = (TextView) rootView.findViewById(R.id.user_info);
		userInfo.setText(new PrePreference(getActivity()).getUser().toString());
		Button quitBtn = (Button) rootView.findViewById(R.id.quit_login);
		quitBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				PrePreference pref = new PrePreference(getActivity());
				pref.removeUser();
				pref.removeUserCourseList();
				Intent i = new Intent(getActivity(), LoginActivity.class);
				i.putExtra("ReLogin", true);
				startActivity(i);
				getActivity().finish();
			}
		});
		return rootView;
	}
}
