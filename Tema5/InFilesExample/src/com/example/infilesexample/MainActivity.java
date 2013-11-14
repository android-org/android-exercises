package com.example.infilesexample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int duration = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Boton para mostrar el AlertDialog
		Button button = (Button) findViewById(R.id.buttonLeer);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				leerFichero();
			}
		});

		try {
			OutputStreamWriter fout = new OutputStreamWriter(openFileOutput(
					"Internal_File.txt", Context.MODE_PRIVATE));
			//"Internal_File.txt", Context.MODE_APPEND));
			fout.write("Escribiendo en el Fichero.");
			fout.close();
		} catch (Exception ex) {
			Log.e("Ficheros", "Error escribiendo en fichero");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void leerFichero() {
		try {
			BufferedReader fin = new BufferedReader(new InputStreamReader(
					openFileInput("Internal_File.txt")));
			String texto = fin.readLine();
			fin.close();

			Toast toast = Toast.makeText(getApplicationContext(), texto,
					duration);
			toast.show();

		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde memoria interna");
		}
	}

}
