package com.example.extfilesexample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
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

		// Boton para escribir fichero en SD
		Button button = (Button) findViewById(R.id.buttonWrite);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (checkExternalMemory()) {
					writeExternalMemory();
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

	public boolean checkExternalMemory() {
		boolean sdDisponible = false;
		boolean sdAccesoEscritura = false;
		// Check estado de la memoria externa (tarjeta SD)
		String estado = Environment.getExternalStorageState();
		if (estado.equals(Environment.MEDIA_MOUNTED)) {
			sdDisponible = true;
			sdAccesoEscritura = true;
		} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			sdDisponible = true;
			sdAccesoEscritura = false;
		} else {
			sdDisponible = false;
			sdAccesoEscritura = false;
		}

		Toast toast = Toast.makeText(getApplicationContext(), "SD Disponible: "
				+ Boolean.valueOf(sdDisponible), duration);
		toast.show();
		toast = Toast.makeText(getApplicationContext(), "SD Escritura: "
				+ Boolean.valueOf(sdAccesoEscritura), duration);
		toast.show();

		if (sdDisponible && sdAccesoEscritura) {
			return true;
		}
		return false;

	}

	public void writeExternalMemory() {

		try {
			File baseSdDir = Environment.getExternalStorageDirectory();
			File f = new File(baseSdDir.getAbsolutePath(), "External_File.txt");
			OutputStreamWriter fout = new OutputStreamWriter(
					new FileOutputStream(f));
			fout.write("Texto de prueba.");
			fout.close();
			Toast toast = Toast.makeText(getApplicationContext(),
					"Fin Escritura", duration);
			toast.show();
		} catch (Exception ex) {
			Log.e("ExtFilesExample", "Error escribiendo fichero en tarjeta SD");
		}
	}
}
