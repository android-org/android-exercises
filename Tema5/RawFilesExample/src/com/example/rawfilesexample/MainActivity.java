package com.example.rawfilesexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int duration = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			InputStream fraw = getResources().openRawResource(
					R.raw.mificheroraw);
			BufferedReader brin = new BufferedReader(
					new InputStreamReader(fraw));
			String linea = brin.readLine();
			fraw.close();

			Toast toast = Toast.makeText(getApplicationContext(), linea,
					duration);
			toast.show();

		} catch (Exception ex) {
			Log.e("RawFilesExample", "Error al leer fichero desde recurso raw");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

}
