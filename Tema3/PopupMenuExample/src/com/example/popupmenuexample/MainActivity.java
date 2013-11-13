package com.example.popupmenuexample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String DEBUG_TAG = "MenuExample";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	// OnClick del Button
	public void showPopup(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.popup_menu, popup.getMenu());

		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {

				switch (item.getItemId()) {
				// Se identifica el item por el Id de cada elemento del menu
				case R.id.MenuPopupCopy:
					Toast.makeText(getApplicationContext(),
							"Clicked popup menu item " + item.getTitle(),
							Toast.LENGTH_SHORT).show();
					return true;
				case R.id.MenuPopupCut:
					Toast.makeText(getApplicationContext(),
							"Clicked popup menu item " + "Menu PopupCut",
							Toast.LENGTH_SHORT).show();
					return true;
				}
				return false;
			}
		});

		popup.show();
	}

}
