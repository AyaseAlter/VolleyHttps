package com.example.volleyhttps;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mybutton;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		mybutton = (Button)this.findViewById(R.id.mybutton);
		mybutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isNetworkConnected()){
					Toast.makeText(MainActivity.this, "请检查手机网络.", Toast.LENGTH_SHORT).show();
				}else{
					mybutton.setEnabled(false);
					BaseAPI baseAPI = new BaseAPI();
					baseAPI.excuteHttp(handler);
				}
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				String data = (String)msg.obj;
				Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
				mybutton.setEnabled(true);
				break;
			default:
				break;
			}
		};
	};
	
	public boolean isNetworkConnected() {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}

}
