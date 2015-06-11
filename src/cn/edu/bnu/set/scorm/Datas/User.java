package cn.edu.bnu.set.scorm.Datas;

import org.json.*;

/**
 * 为了数据交换方便
 * enableDate, disableDate, time 三个属性在此处先使用String存储
 * 在主程序中需要使用时，再使用DateFormat工具转为Date对象
 * @author Henry Lau
 *
 */
public class User {
	private static final String JSON_ID = "id";
	private int id;
	private static final String JSON_USERNAME = "username";
	private String userName;
	private static final String JSON_PASSWORD = "password";
	private String passWord;
	private static final String JSON_REALNAME = "realname";
	private String realName;
	private static final String JSON_NUMBER = "number";
	private String number;
	private static final String JSON_MAIL = "mail";
	private String mail;
	private static final String JSON_ENABLE = "enabledate";
	private String enableDate;
	private static final String JSON_DISABLE = "disabledate";
	private String disableDate;
	private static final String JSON_STATE = "state";
	private String state;
	private static final String JSON_TIME = "time";
	private String time;
	private static final String JSON_SEX = "sex";
	private String sex;
	private static final String JSON_ROLE = "role";
	private int role;
	private static final String JSON_DELETED = "deleted";
	private int deleted;
	private static final String JSON_INFO = "info";
	private String info;

	public User(JSONObject json) throws JSONException {
		id = json.getInt(JSON_ID);
		userName = json.getString(JSON_USERNAME);
		passWord = json.getString(JSON_PASSWORD);
		realName = json.getString(JSON_REALNAME);

		if (json.has(JSON_NUMBER))
			number = json.getString(JSON_NUMBER);
		if (json.has(JSON_MAIL))
			mail = json.getString(JSON_MAIL);
		if (json.has(JSON_ENABLE))
			enableDate = json.getString(JSON_ENABLE);
		if (json.has(JSON_DISABLE))
			disableDate = json.getString(JSON_DISABLE);
		if (json.has(JSON_STATE))
			state = json.getString(JSON_STATE);
		if (json.has(JSON_TIME))
			time = json.getString(JSON_TIME);
		if (json.has(JSON_SEX))
			sex = json.getString(JSON_SEX);
		if (json.has(JSON_ROLE))
			role = json.getInt(JSON_ROLE);
		if (json.has(JSON_DELETED))
			deleted = json.getInt(JSON_DELETED);
		if (json.has(JSON_INFO))
			info = json.getString(JSON_INFO);
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, id);
		json.put(JSON_USERNAME, userName);
		json.put(JSON_PASSWORD, passWord);
		json.put(JSON_REALNAME, realName);
		if (number != null)
			json.put(JSON_NUMBER, number);
		if (mail != null)
			json.put(JSON_MAIL, mail);
		if (enableDate != null)
			json.put(JSON_ENABLE, enableDate);
		if (disableDate != null)
			json.put(JSON_DISABLE, disableDate);
		if (state != null)
			json.put(JSON_STATE, state);
		if (time != null)
			json.put(JSON_TIME, time);
		if (sex != null)
			json.put(JSON_SEX, sex);
		json.put(JSON_ROLE, role);
		json.put(JSON_DELETED, deleted);
		json.put(JSON_INFO, info);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(String enableDate) {
		this.enableDate = enableDate;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "UserName: " + userName + "\n" + "RealName: " + realName + "\n"
				+ info;
	}

}
