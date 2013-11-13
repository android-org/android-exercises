package com.example.dialogexample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final int DIALOGO_TIPO_ALERTA = 1;
	private static final int DIALOGO_TIPO_CONFIRMACION = 2;
	private static final int DIALOGO_TIPO_SELECCION = 3;
	private static final int DIALOGO_TIPO_SELECCION_SINGLE_CHOICE = 4;
 
	private int duration = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Boton para mostrar el AlertDialog
		Button button = (Button) findViewById(R.id.buttonAlert);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(1);
			}
		});

		// Boton para mostrar el Confirmation Dialog
		Button button2 = (Button) findViewById(R.id.buttonConfirm);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(2);
			}
		});

		// Boton para mostrar el Select Dialog
		Button button3 = (Button) findViewById(R.id.buttonSelect);
		button3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(3);
			}
		});

		Button button4 = (Button) findViewById(R.id.buttonSelSingleChoice);
		button4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(4);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// CallBack
	protected Dialog onCreateDialog(int id) {
		Dialog dialogo = null;
		switch (id) {
		case DIALOGO_TIPO_ALERTA:
			dialogo = crearDialogoAlerta();
			break;
		case DIALOGO_TIPO_CONFIRMACION:
			dialogo = crearDialogoConfirmacion();
			break;
		case DIALOGO_TIPO_SELECCION:
			dialogo = crearDialogoSeleccion();
			break;
		case DIALOGO_TIPO_SELECCION_SINGLE_CHOICE:
			dialogo = crearDialogoSeleccionSingleChoice();
			break;
		default:
			dialogo = null;
			break;
		}
		return dialogo;
	}

	private Dialog crearDialogoAlerta() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("My Alert Dialog");
		builder.setMessage("Texto Mensaje de la Alerta");
		builder.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoConfirmacion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("My Confirmation Dialog");
		builder.setMessage("¿Confirma lo que queria pulsa el boton?");
		builder.setPositiveButton("Aceptar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Aceptado", duration);
				toast.show();
				dialog.cancel();
			}
		});
		builder.setNegativeButton("Cancelar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Cancelado", duration);
				toast.show();
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoSeleccion() {
		final String[] items = { "Rojo", "Amarillo", "Verde" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("My Select Dialog");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Toast toast = Toast.makeText(getApplicationContext(), "Item: "
						+ items[item], duration);
				toast.show();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoSeleccionSingleChoice() {
		final String[] items = { "Rojo", "Amarillo", "Verde" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("My Sel Choice Dialog");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"Item: " + items[item], duration);
						toast.show();
					}
				});
		return builder.create();
	}

}
