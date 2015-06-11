package cn.edu.bnu.set.scorm.Net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import cn.edu.bnu.set.scorm.Datas.LearningResource;
import android.util.Log;

public class XMLResourceFetchr {
	public static final String SERVER_PATH = "http://192.168.191.1/";

	byte[] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally {
			connection.disconnect();
		}
	}

	public String getUrl(String urlSpec) throws IOException {
		return new String(getUrlBytes(urlSpec));
	}

	public ArrayList<LearningResource> fetchItems(String xmlName) {
		ArrayList<LearningResource> items = new ArrayList<LearningResource>();
		try {
			String url = SERVER_PATH + xmlName;
			String xmlString = getUrl(url);
			Log.i("ResourceFetchr", "Received "+ xmlName +": " + xmlString);
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			parseItems(items, parser);
		} catch (IOException ioe) {
			Log.e("TAG", "Failed to fetch items", ioe);
		} catch (XmlPullParserException xppe) {
			Log.e("TAG", "Failed to parse items", xppe);
		}
		return items;
	}

	void parseItems(ArrayList<LearningResource> items, XmlPullParser parser)
			throws XmlPullParserException, IOException {
		int eventType = parser.next();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG
					&& "item".equals(parser.getName())) {
				String name = parser.getAttributeValue(null, "name");
				String url_s = parser.getAttributeValue(null, "url_s");
				items.add(new LearningResource(name, url_s));
			}
			eventType = parser.next();
		}
	}
}
