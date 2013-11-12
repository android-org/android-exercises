package com.example.listviewexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Se recupera la GridView que contendrá los items
		GridView menuGrid = (GridView) findViewById(R.id.GridViewMenu);
		
		// Creo items para alimentar el grid via Adapter
		String[] arrayItems = { "Item1", "Item2", "Item3", "Item4", "Item5",
				"Item6", "Item7", "Item8", "Item9" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.menu_item, arrayItems);

		// Con un Resource Array para los datos, en lugar de definirlos en codigo
		// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		// this, R.array.mi_item_array, R.layout.menu_item);
		
		// Utilizando un Layout item definido
		// ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
		// R.layout.menu_item, items);
		

		// Se carga LA GridView con el Adapter
		menuGrid.setAdapter(adapter);

		// Listener para los Items
		menuGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked,
					int position, long id) {
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				TextView textViewPulsado = (TextView) itemClicked;
				String strText = textViewPulsado.getText().toString();
				// Comparo el texto recuperado con su string en R
				if (strText.equals("Item1")) {
					// Pulsado Item1
					Toast toast = Toast.makeText(context,
							"Pulsado primer Item: " + strText, duration);
					toast.show();
				} else {

					// Pulsado Item != de Item1
					Toast toast = Toast.makeText(context, "Pulsado:" + strText,
							duration);
					toast.show();

				}

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
