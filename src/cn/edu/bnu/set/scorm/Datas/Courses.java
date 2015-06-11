package cn.edu.bnu.set.scorm.Datas;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 课程、课程组和课程单元组共用的数据类型 通过type属性的值来区分 课程组type=3，课程单元组type=2
 * 课程和课程单元在服务器返回的Json中没有设置type值 可以设置为课程type=1，课程单元type=0
 * 
 * @author Henry Lau
 * 
 */
public class Courses {
	private static final int TYPE_COURSE_UNIT = 0;
	private static final int TYPE_COURSE = 1;
	private static final int TYPE_COURSE_UNIT_GROUP = 2;
	private static final int TYPE_COURSE_GROUP = 3;
	private static String JSON_ID = "id";
	private int id;
	private static String JSON_TITLE = "title";
	private String title;
	private static String JSON_DESC = "description";
	private String description;
	private static String JSON_NUM = "number";
	private String number;
	private static String JSON_START = "start";
	private String starttime;
	private static String JSON_END = "endtime";
	private String endtime;
	private static String JSON_KEY = "key";
	private String key;
	private static String JSON_TIME = "time";
	private String time;
	private static String JSON_DELETED = "deleted";
	private int deleted;
	private static String JSON_USERID = "userid";
	private int userid;
	private static String JSON_ALWAYS = "alwaysupdate";
	private int alwaysupdate;
	private static String JSON_TYPE = "type";
	private int type;
	private static String JSON_URL = "url";
	private String url;

	/**
	 * 带type参数的Json构造方法 用于构造课程或课程单元
	 * 
	 * @param json
	 * @param type
	 * @throws JSONException
	 */
	public Courses(JSONObject json, int type) throws JSONException {
		this.type = type;
		if (type == TYPE_COURSE_UNIT) {
			title = json.getString(JSON_TITLE);
			url = json.getString(JSON_URL);
		} else if (type == TYPE_COURSE) {
			id = json.getInt(JSON_ID);//
			title = json.getString(JSON_TITLE);
			description = json.getString(JSON_DESC);
			number = json.getString(JSON_NUM);
			starttime = json.getString(JSON_START);
			endtime = json.getString(JSON_END);
			key = json.getString(JSON_KEY);
			time = json.getString(JSON_TIME);
			deleted = json.getInt(JSON_DELETED);
			userid = json.getInt(JSON_USERID);
			alwaysupdate = json.getInt(JSON_ALWAYS);
		}
	}

	/**
	 * 不带type参数的构造方法，用于构造课程组或课程单元组
	 * 
	 * @param json
	 * @throws JSONException
	 */
	public Courses(JSONObject json) throws JSONException {
		id = json.getInt(JSON_ID);
		title = json.getString(JSON_TITLE);
		description = json.getString(JSON_DESC);
		userid = json.getInt(JSON_USERID);
		type = json.getInt(JSON_TYPE);
		time = json.getString(JSON_TIME);
		deleted = json.getInt(JSON_DELETED);
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_TITLE, title);
		json.put(JSON_TYPE, type);
		switch (type) {
		case TYPE_COURSE_UNIT:
			json.put(JSON_URL, url);
			break;
		case TYPE_COURSE:
			json.put(JSON_NUM, number);
			json.put(JSON_START, starttime);
			json.put(JSON_END, endtime);
			json.put(JSON_KEY, key);
			json.put(JSON_ALWAYS, alwaysupdate);
		case TYPE_COURSE_UNIT_GROUP:
		case TYPE_COURSE_GROUP:
			json.put(JSON_ID, id);
			json.put(JSON_TITLE, title);
			json.put(JSON_DESC, description);
			json.put(JSON_USERID, userid);
			json.put(JSON_TIME, time);
			json.put(JSON_DELETED, deleted);
		}
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAlwaysupdate() {
		return alwaysupdate;
	}

	public void setAlwaysupdate(int alwaysupdate) {
		this.alwaysupdate = alwaysupdate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
