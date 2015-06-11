package cn.edu.bnu.set.scorm;

import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Datas.User;
import cn.edu.bnu.set.scorm.Net.LoginWorker;
import cn.edu.bnu.set.scorm.Util.PrePreference;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class LoginActivity extends Activity {
	private EditText userNameEditer = null;
	private EditText passWordInputer = null;
	private EditText urlInputer = null;
	private Button loginButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent i = getIntent();
		boolean reLogin = i.getBooleanExtra("ReLogin", false);
		if (!reLogin && hasLogin()) {
			goToMain();
			return;
		}
		userNameEditer = (EditText) findViewById(R.id.username_edit);
		passWordInputer = (EditText) findViewById(R.id.password_edit);
		urlInputer = (EditText) findViewById(R.id.hosturl_edit);
		loginButton = (Button) findViewById(R.id.signin_button);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new PrePreference(LoginActivity.this).save(
						PrePreference.URL_API, urlInputer.getText().toString());
				new LoginWorker(LoginActivity.this, userNameEditer.getText()
						.toString(), passWordInputer.getText().toString())
						.execute();
			}
		});
	}

	public boolean hasLogin() {
		boolean has = false;
		has = (new PrePreference(this).getUser() != null);
		return has;
	}

	/**
	 * 供LoginWorker回调的方法，解析Json格式的登录结果并返回
	 * 
	 * @param result
	 * @param user
	 */
	public void setLoginResult(User user) {
		PrePreference pref = new PrePreference(this);
		pref.saveUser(user);
	}

	public void goToMain() {
		Toast.makeText(this, "欢迎回到学习系统", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

}
