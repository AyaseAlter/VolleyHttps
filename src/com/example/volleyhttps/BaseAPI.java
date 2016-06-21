package com.example.volleyhttps;

import java.util.HashMap;
import android.os.Handler;
import android.os.Message;
import com.MyApplication;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.SsX509TrustManager;

public class BaseAPI {

	public <T> void excuteHttp(Handler handler) {
		Message loadMsg = new Message();
		loadMsg.setTarget(handler);

		String url = "https://secure.msee.tv/info/update?os=android&ver=1.0.1";

		ResponseListener<T> listener = new ResponseListener<T>(loadMsg);
		ErrorResponseListener errorListener = new ErrorResponseListener();
		SsX509TrustManager.allowAllSSL();
		JsonRequest<T> request = new JsonRequest<T>(Method.GET, url, listener,
				errorListener, new HashMap<String, String>());
		RetryPolicy retryPolicy = new DefaultRetryPolicy(1000 * 15, 0, 0);
		request.setRetryPolicy(retryPolicy);
		MyApplication.getInstance().addToRequestQueue(request, null);
	}

}
