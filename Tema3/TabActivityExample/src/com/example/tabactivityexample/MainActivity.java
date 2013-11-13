package com.example.tabactivityexample;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabs = getTabHost();
		// Config Tab1
		TabSpec spec1 = tabs.newTabSpec("TabEtiqueta1");
		spec1.setIndicator("Compass",
				getResources().getDrawable(android.R.drawable.ic_menu_compass));
		Intent in1 = new Intent(this, TabActivity1.class);
		spec1.setContent(in1);
		tabs.addTab(spec1);

		// Config Tab2
		TabSpec spec2 = tabs.newTabSpec("TabEtiqueta2");
		spec2.setIndicator("Agenda",
				getResources().getDrawable(android.R.drawable.ic_menu_agenda));
		Intent in2 = new Intent(this, TabActivity2.class);
		spec2.setContent(in2);
		tabs.addTab(spec2);

		// Mostramos activo el primero
		tabs.setCurrentTab(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
