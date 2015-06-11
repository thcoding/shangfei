package cn.edu.bnu.set.scorm.Net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import cn.edu.bnu.set.scorm.LoginActivity;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Datas.User;
import cn.edu.bnu.set.scorm.Util.*;

public class LoginWorker extends AsyncTask<Void, Long, String> {
	
	private String testJSON = "{ \"result\": 1, \"object\": { \"id\": \"2\", \"username\": \"stu1\", \"password\": \"e10adc3949ba59abbe56e057f20f883e\", \"realname\": \"张同学\", \"number\": null, \"mail\": null, \"enabledate\": null, \"disabledate\": null, \"state\": \"Y\", \"time\": \"2014-10-12 12:07:33\", \"sex\": null, \"role\": \"3\", \"deleted\": \"0\", \"info\": \"登录成功\" } }";

	private List<NameValuePair> paramsPost;
	private LoginActivity act;
	private String urlLogin;

	public LoginWorker(LoginActivity act, String userName, String passWord) {
		super();
		this.paramsPost = new ArrayList<NameValuePair>();
		JSONObject json = new JSONObject();
		String postString = null;
		try {
			json.put("moduleid", "1");
			json.put("username", userName);
			json.put("password", passWord);
			postString = json.toString();
			Log.i("HTTP-POST-TEST","PostString: " + postString);
			postString = new String(CodeUtil.base64_encode(postString.getBytes()));
		} catch (Exception e) {

		}
		paramsPost.add(new BasicNameValuePair("json", postString));
		this.act = act;
		this.urlLogin = new PrePreference(act).getString(PrePreference.URL_API, "http://192.168.108.143/shang/api/LMSApi.php");
		/*this.urlLogin = act.getText(R.string.url_host).toString()
				+ act.getText(R.string.url_api).toString();*/
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... arg0) {
		String result = "";
		if(urlLogin.equals("local")){
			result = testJSON;
		} else{
			result = Net.doPost(urlLogin, paramsPost);
			try {
				result = CodeUtil.base64_decode(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	protected void onPostExecute(String jsonString) {
		super.onPostExecute(jsonString);
		Log.i("HTTP-POST-TEST", "GetString: " + jsonString);
		JSONObject json = null;
		try {
			json = new JSONObject(jsonString);
			int result = json.getInt("result");
			if (result == 0)
				return;
			User user = new User(json.getJSONObject("object"));
			act.setLoginResult(user);
			//act.checkLogin();
			act.goToMain();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}