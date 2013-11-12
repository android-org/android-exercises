package com.example.listviewexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ArrayAdapter;
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

		// Recupero que Lista que contendrá los Items
		ListView menuList = (ListView) findViewById(R.id.ListViewMenu);

		// Creo items para alimentar la lista via Adapter
		String[] items = new String[] { "Mi Item1", "Mi Item2", "Mi Item3",
				"Mi Item4", "Mi Item5", "Mi Item6", "Mi Item7", "Mi Item8" };

		// Utilizando un Layout item de sistema simple
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		// Utilizando un Layout item definido
		// ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
		// R.layout.menu_item, items);

		// Utilizado un Resource Array para los datos, en lugar de definirlos en
		// codigo
		// ArrayAdapter<CharSequence> adapt =
		// ArrayAdapter.createFromResource(this, R.array.mi_item_array,
		// R.layout.menu_item);

		// Se carga la Lista con el Adapter
		menuList.setAdapter(adapt);

		// Listener para los Items
		menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked,
					int position, long id) {
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				TextView textViewPulsado = (TextView) itemClicked;
				String strText = textViewPulsado.getText().toString();
				// Comparo el texto recuperado con su string en R
				if (strText.equals("Mi Item1")) {
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
