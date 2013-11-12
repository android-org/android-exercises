package com.example.spinnerexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		final int duration = Toast.LENGTH_SHORT;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Recupero que Lista que contendrá los Items
		Spinner spinner = (Spinner) findViewById(R.id.miSpinner);
		// Creo Adapter utilizado un Resource de array y un layout standard para
		// el item seleccionado
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.spinner_array,
				android.R.layout.simple_spinner_item);
		// Defino Layout asociado a la lista de items desplegable
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Aplico el Adaptar al Spinner
		spinner.setAdapter(adapter);

		// Listener para la seleccion
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					android.view.View v, int position, long id) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"posicion sel: " + position, duration);
				toast.show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Nothing Selected", duration);
				toast.show();
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
