package com;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

	public static final String TAG = MyApplication.class.getSimpleName();
	
	private static MyApplication instance;
	private RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static synchronized MyApplication getInstance() {
		return instance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, Object tag) {
		// set the default tag if tag is empty
		req.setTag(tag == null ? TAG : tag);
		VolleyLog.d("Adding request to queue: %s", req.getUrl());
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		// set the default tag if tag is empty
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
