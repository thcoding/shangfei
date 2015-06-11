package cn.edu.bnu.set.scorm;

import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.R.anim;
import cn.edu.bnu.set.scorm.R.layout;
import cn.edu.bnu.set.scorm.R.string;
import cn.edu.bnu.set.scorm.Util.PrePreference;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	private static final int FAILURE = 0; // 失败
	private static final int SUCCESS = 1; // 成功
	private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式
	private static final int SHOW_TIME_MIN = 2000;

	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		new AsyncTask<Void, Void, Integer>() {

			@Override
			protected Integer doInBackground(Void... params) {
				int result = 0;
				long startTime = System.currentTimeMillis();
				long loadingTime = System.currentTimeMillis() - startTime;
				if (loadingTime < SHOW_TIME_MIN) {
					try {
						Thread.sleep(SHOW_TIME_MIN - loadingTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return result;
			}

			@Override
			protected void onPostExecute(Integer result) {
				Intent intent = new Intent(WelcomeActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			};
		}.execute();

	}

	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			return this.getString(R.string.version_name) + version;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
