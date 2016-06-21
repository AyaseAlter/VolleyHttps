package com.example.volleyhttps;

import android.os.Handler;
import android.os.Message;
import com.android.volley.Response.Listener;

public class ResponseListener<T> implements Listener<T> {

	private Handler handler;
	private Message msg;

	public ResponseListener(Message message) {
		this.msg = message;
		handler = msg.getTarget();
	}

	@Override
	public void onResponse(T response) {
		Message message = new Message();
		message.what = 0;
		message.arg1 = msg.arg1;
		message.obj = response;
		handler.sendMessage(message);
	}
	
}
