package cn.edu.bnu.set.scorm.Net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import cn.edu.bnu.set.scorm.MainActivity;
import cn.edu.bnu.set.scorm.R;
import cn.edu.bnu.set.scorm.Util.CodeUtil;
import cn.edu.bnu.set.scorm.Util.Net;
import cn.edu.bnu.set.scorm.Util.PrePreference;

public class CourseListWorker extends AsyncTask<Void, Long, String> {

	private String testJSONm2 = "{ \"result\": 1, \"list\": { \"course\": [ { \"id\": \"9\", \"title\": \"APU基础\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-20 22:40:52\", \"endtime\": \"2014-10-23 22:40:54\", \"key\": null, \"time\": \"2014-10-19 22:40:57\", \"deleted\": \"0\", \"userid\": \"4\", \"alwaysupdate\": \"1\" }, { \"id\": \"8\", \"title\": \"第七个课程\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-23 11:42:47\", \"endtime\": \"2014-10-30 11:42:48\", \"key\": null, \"time\": \"2014-10-19 11:42:52\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"0\" }, { \"id\": \"5\", \"title\": \"发动机常见问题解答\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-30 11:33:44\", \"key\": null, \"time\": \"2014-10-17 15:49:14\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\" }, { \"id\": \"4\", \"title\": \"第四个课程\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-19 11:33:44\", \"key\": null, \"time\": \"2014-10-07 19:38:28\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\" }, { \"id\": \"3\", \"title\": \"第三个课程3\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-30 11:33:44\", \"key\": null, \"time\": \"2014-10-07 19:37:18\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\" } ], \"courseGroup\": [ { \"id\": \"12\", \"title\": \"APU课程组\", \"description\": null, \"userid\": \"4\", \"type\": \"3\", \"time\": \"2014-10-19 22:53:32\", \"deleted\": \"0\" }, { \"id\": \"6\", \"title\": \"第一个课程组\", \"description\": null, \"userid\": \"1\", \"type\": \"3\", \"time\": \"2014-10-07 19:28:36\", \"deleted\": \"0\" } ] } }";
	private String testJSONm3 = "{ \"result\": 1, \"list\": { \"courseUnit\": [ { \"title\": \"APU基础第一讲\", \"url\": \"http://www.bnu.edu.cn/index.html\" }, { \"title\": \"tomcat启动与类加载\", \"url\": \"http://www.bnu.edu.cn/bsdkx/76166.html\" } ], \"courseUnitGroup\": [ { \"id\": \"11\", \"title\": \"APU基础组\", \"description\": null, \"userid\": \"4\", \"type\": \"2\", \"time\": \"2014-10-19 22:45:26\", \"deleted\": \"0\" }, { \"id\": \"3\", \"title\": \"第1个课程单元组\", \"description\": null, \"userid\": \"1\", \"type\": \"2\", \"time\": \"2014-10-07 16:07:32\", \"deleted\": \"0\" } ] } }";
	private String testJSONm4 = "{ \"result\": 1, \"list\": { \"courseUnit\": [ { \"id\": \"15\", \"title\": \"第一个学习路径\", \"description\": null, \"number\": null, \"key\": null, \"enabledate\": null, \"disabledate\": null, \"totalscore\": null, \"passscore\": null, \"timelength\": null, \"type\": null, \"time\": \"2014-10-17 15:36:39\", \"deleted\": \"0\", \"lpid\": \"7\", \"userid\": \"1\" }, { \"id\": \"13\", \"title\": \"wwwww\", \"description\": null, \"number\": null, \"key\": null, \"enabledate\": null, \"disabledate\": null, \"totalscore\": null, \"passscore\": null, \"timelength\": null, \"type\": null, \"time\": \"2014-10-17 15:29:57\", \"deleted\": \"0\", \"lpid\": \"0\", \"userid\": \"1\" }, { \"id\": \"9\", \"title\": \"Golf Explained - Minimum Run-time Calls\", \"description\": null, \"number\": null, \"key\": null, \"enabledate\": null, \"disabledate\": null, \"totalscore\": null, \"passscore\": null, \"timelength\": null, \"type\": null, \"time\": \"2014-10-17 14:14:24\", \"deleted\": \"0\", \"lpid\": \"2\", \"userid\": \"1\" } ], \"courseUnitGroup\": [ { \"id\": \"11\", \"title\": \"APU基础组\", \"description\": null, \"userid\": \"4\", \"type\": \"2\", \"time\": \"2014-10-19 22:45:26\", \"deleted\": \"0\" }, { \"id\": \"3\", \"title\": \"第1个课程单元组\", \"description\": null, \"userid\": \"1\", \"type\": \"2\", \"time\": \"2014-10-07 16:07:32\", \"deleted\": \"0\" } ] } }";
	private String testJSONm5 = "{ \"result\": 1, \"list\": { \"course\": [ { \"id\": \"5\", \"title\": \"发动机常见问题解答\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-30 11:33:44\", \"key\": null, \"time\": \"2014-10-17 15:49:14\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\", \"type\": \"1\" }, { \"id\": \"2\", \"title\": \"第二个课程\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-30 11:33:44\", \"key\": null, \"time\": \"2014-10-07 01:37:14\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\", \"type\": \"1\" }, { \"id\": \"1\", \"title\": \"第一个课程1\", \"description\": null, \"number\": null, \"starttime\": \"2014-10-18 11:34:11\", \"endtime\": \"2014-10-30 11:33:44\", \"key\": null, \"time\": \"2014-10-07 01:22:06\", \"deleted\": \"0\", \"userid\": \"1\", \"alwaysupdate\": \"1\", \"type\": \"1\" } ], \"courseGroup\": [ { \"id\": \"6\", \"title\": \"第一个课程组\", \"description\": null, \"userid\": \"1\", \"type\": \"3\", \"time\": \"2014-10-07 19:28:36\", \"deleted\": \"0\" } ] } }";

	private List<NameValuePair> paramsPost;
	private MainActivity act;
	private int moduleId;
	private String urlCourse;
	private int id;

	public CourseListWorker(MainActivity act, int moduleId, int id) {
		super();
		this.paramsPost = new ArrayList<NameValuePair>();
		JSONObject json = new JSONObject();
		String postString = null;
		try {
			json.put("moduleid", moduleId);
			if (moduleId == 2) {
				json.put("userid", id);
			} else if (moduleId == 3) {
				json.put("courseid", id);
			} else if (moduleId == 4 || moduleId == 5) {
				json.put("groupid", id);
			}
			postString = json.toString();
			Log.i("HTTP-POST-TEST", "PostString: " + postString);
			postString = new String(CodeUtil.base64_encode(postString
					.getBytes()));
		} catch (Exception e) {

		}
		paramsPost.add(new BasicNameValuePair("json", postString));
		this.act = act;
		this.moduleId = moduleId;
		this.id = id;
		this.urlCourse = new PrePreference(act).getString(
				PrePreference.URL_API, "");
		/*this.urlCourse = act.getText(R.string.url_host).toString()
				+ act.getText(R.string.url_api).toString();*/
	}

	@Override
	protected String doInBackground(Void... arg0) {
		String result = null;
		if (urlCourse.equals("local")) {
			if (moduleId == 2) {
				result = testJSONm2;
			} else if (moduleId == 3) {
				result = testJSONm3;
			} else if (moduleId == 4) {
				result = testJSONm4;
			} else if (moduleId == 5) {
				result = testJSONm5;
			}
		} else {
			result = Net.doPost(urlCourse, paramsPost);
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
			JSONObject list = json.getJSONObject("list");
			switch (moduleId) {
			case 2:
				act.setCourseLists(list);
				break;
			case 3:
			case 4:
				act.setCourseUnitLists(list, id);
				break;
			case 5:
				act.setCourseListOfCourseGroup(list, id);
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
