package cn.edu.bnu.set.scorm.Datas;


public class LearningResource {
	private String mTitle;
	private String mUrl;

	public LearningResource(String title, String url) {
		super();
		mTitle = title;
		mUrl = url;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getUrl() {
		return mUrl;
	}

	public String toString() {
		return mTitle + " " + mUrl;
	}

}
