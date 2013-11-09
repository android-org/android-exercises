package com.example.fromactivitytofragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FutureFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent received = getIntent();
		String urlReceived = received.getStringExtra(MainActivity.EXTRA_URL);
		setContentView(R.layout.activity_future_fragment);
		WebView webview = (WebView) findViewById(R.id.webView1);
		//Creamos un webviewclient para que no nos abra el navegador
		webview.setWebViewClient(new WebViewClient()); 
		webview.loadUrl(urlReceived);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.future, menu);
		return true;
	}

}
