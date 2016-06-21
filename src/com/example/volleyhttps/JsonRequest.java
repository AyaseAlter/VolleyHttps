package com.example.volleyhttps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

public class JsonRequest<T> extends Request<T> {

	private final Listener<T> mListener;
	private Map<String, String> postdata;

	public JsonRequest(int method, String url, Listener<T> listener,
			ErrorListener errorListener, Map<String, String> postData) {
		super(method, url, errorListener);
		this.mListener = listener;
		this.postdata = postData;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		Iterator iter = postdata.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (key != null && val != null) {
			}
		}
		return postdata;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		HashMap<String, String> headers = new HashMap<String, String>();
		return headers;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		String json = null;
		try {
			json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return (Response<T>) Response.success(json,
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		if (null != mListener && null != response) {
			mListener.onResponse(response);
		}
	}

	@Override
	public void deliverError(VolleyError error) {
		if (null != mListener && null != error) {
			mListener.onResponse((T) error);
		}
	}
}
