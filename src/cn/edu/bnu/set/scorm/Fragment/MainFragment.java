package cn.edu.bnu.set.scorm.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.R.layout;
import cn.edu.bnu.set.scorm.R.string;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        getActivity().setTitle(R.string.app_name); 
        return rootView;
    }
}
