package com.example.tabhostexample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();
		// Config Tab1
		TabSpec spec = tabs.newTabSpec("TabEtiqueta1");
		spec.setContent(R.id.tab1);
		//Android 4: label and Icon Mutex
		spec.setIndicator("Compass",//"" to Android 4
				getResources().getDrawable(android.R.drawable.ic_menu_compass));
		tabs.addTab(spec);
		
		// Config Tab2
		spec = tabs.newTabSpec("TabEtiqueta2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Agenda",
				getResources().getDrawable(android.R.drawable.ic_menu_agenda));
		tabs.addTab(spec);
		
		//Mostramos activo el primero
		tabs.setCurrentTab(0);

		// Listener para el TabHost
		tabs.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
