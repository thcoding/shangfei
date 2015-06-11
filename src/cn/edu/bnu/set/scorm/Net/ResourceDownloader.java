package cn.edu.bnu.set.scorm.Net;

import java.io.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.*;
import android.util.*;

public class ResourceDownloader<Token> extends HandlerThread {
	private static final String TAG = "ResourceDownloader";
	private static final int MESSAGE_DOWNLOAD = 0;
	private static final String SAVE_PATH = "data/data/cn.edu.bnu.set.scorm/files/";

	Handler mHandler;
	Map<Token, String> requestMap = Collections
			.synchronizedMap(new HashMap<Token, String>());
	Handler mResponseHandler;
	Listener<Token> mListener;
	Context mContext;

	public interface Listener<Token> {
		void onResourceDownloaded(Token token, boolean isDownloaded);
	}

	public void setListener(Listener<Token> listener) {
		mListener = listener;
	}

	public ResourceDownloader(Handler responseHandler, Context context) {
		super(TAG);
		mResponseHandler = responseHandler;
		mContext = context;
	}

	public void queueResource(Token token, String jsonItem) {
		requestMap.put(token, jsonItem);
		mHandler.obtainMessage(MESSAGE_DOWNLOAD, token).sendToTarget();
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onLooperPrepared() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == MESSAGE_DOWNLOAD) {
					@SuppressWarnings("unchecked")
					Token token = (Token) msg.obj;
					Log.i(TAG,
							"Got a request for url: " + requestMap.get(token));
					handleRequest(token);
				}
			}
		};
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void handleRequest(final Token token) {
		try {
			final String json = requestMap.get(token);
			if(json == null)
				return;
			JSONObject item = new JSONObject(json);
			String title = item.getString("title");
			String url = item.getString("url");
			Log.i(TAG, url);
			Log.i(TAG, title);
			File newFile = new File(SAVE_PATH + title + ".html");
			if (!newFile.exists()) {

				byte[] bytes = new XMLResourceFetchr().getUrlBytes(url);
				// save
				FileOutputStream outStream = null;

				outStream = mContext.openFileOutput(title + ".html",
						Context.MODE_WORLD_READABLE);
				outStream.write(bytes);
				outStream.close();
			}
			mResponseHandler.post(new Runnable() {
				@Override
				public void run() {
					if (requestMap.get(token) != json)
						return;
					requestMap.remove(token);
					mListener.onResourceDownloaded(token, true);
				}
			});
		} catch (IOException ioe) {
			Log.e(TAG, "Error downloading resource", ioe);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void clearQueue() {
		mHandler.removeMessages(MESSAGE_DOWNLOAD);
		requestMap.clear();
	}

}
