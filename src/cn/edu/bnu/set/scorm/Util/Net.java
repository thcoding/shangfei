package cn.edu.bnu.set.scorm.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class Net {
	public static interface Done {
		public void done(String result);
	}

	public static final String doGet(final String url) {
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		InputStream is = null;
		String result = "";
		try {
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			// Log.d("web", result);
			// System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static final String doPost(String url) {
		return doPost(url, null);
	}

	public static final String doPost(final String url,
			final List<NameValuePair> params) {
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		InputStream is = null;
		String result = "";
		try {
			if (params != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {		
			ex.printStackTrace();
		}
		return result;
	}

	public static final void doGetNewThread(final String url, final Done done) {
		new Thread() {
			public void run() {
				HttpGet httpGet = new HttpGet(url);
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = null;
				HttpEntity httpEntity = null;
				InputStream is = null;
				try {
					httpResponse = httpClient.execute(httpGet);
					httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					String result = "";
					String line = "";
					while ((line = br.readLine()) != null) {
						result += line;
					}
					done.done(result);
					// Log.d("web", result);
					// System.out.println(result);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}.start();
		;
	}
}
