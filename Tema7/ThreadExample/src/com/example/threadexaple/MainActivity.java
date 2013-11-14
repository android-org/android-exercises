package com.example.threadexaple;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView lblTexto;
	private EditText txtTexto;
	private Button btnANR;
	private Button btnThreadNotUI;
	private Button buttonThreadHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTexto = (TextView) findViewById(R.id.LblEtiqueta);
		txtTexto = (EditText) findViewById(R.id.TxtTexto);
		btnANR = (Button) findViewById(R.id.ButtonANR);
		btnThreadNotUI = (Button) findViewById(R.id.ButtonThreadNotUI);
		buttonThreadHandler = (Button) findViewById(R.id.ButtonThreadHandler);

		// Button que provoca un ANR
		btnANR.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				try {
					// Tarea pesada: p.e Http Request
					Thread.sleep(10000);
				} catch (Exception e) {
				}
			}
		});

		// Button que provoca una Excepcion por permisos
		btnThreadNotUI.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				// Crear nuevo Thread e intentar modificar el TextView
				new Thread(new Runnable() {
					public void run() {
						lblTexto.setText(txtTexto.getText().toString());
					}
				}).start();

			}
		});

		
		// Button que lanza Thread con Handler
		buttonThreadHandler.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				try {
					// Thread con Handler
					new Thread() {
						public void run() {
							try {

								// Cambiamos color del Button
								Message message = new Message();
								message.what = 1;
								getHandler.sendMessage(message);

								// Proceso largo en Background
								Thread.sleep(3000);
								// Cambiamos el color del boton a amarillo
								Message message2 = new Message();
								message2.what = 2;
								getHandler.sendMessage(message2);
								Thread.sleep(8000);

								// Restauramos color del Button
								Message message3 = new Message();
								message3.what = 3;
								getHandler.sendMessage(message3);

							} catch (Exception ex) {
								Log.e(MainActivity.class.toString(),
										ex.getMessage(), ex);
							}
						}

					}.start();
				} catch (Exception e) {
				}
			}
		});

	}

	// Handler: recibe los Messages enviados por el Thread
	// Capaz de actuar sobre la UI
	private Handler getHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// la tarea en segundo plano ya ha terminado
			Builder builder = new Builder(MainActivity.this);
			if (msg.what == 1) {
				// Cambiamos color del Boton
				buttonThreadHandler.setBackgroundColor(Color.BLUE);
				builder.setTitle("Empezando Tarea pesada");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setMessage("Empezando Tarea Pesada");
				builder.setNeutralButton("OK", null);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				alertDialog.setCancelable(false);
				lblTexto.setText("Handler cambio el color a azul");
			} else if (msg.what == 2) {
				buttonThreadHandler.setBackgroundColor(Color.YELLOW);
				lblTexto.setText("Handler cambio el color a amarillo");
			} else if (msg.what == 3) {
				buttonThreadHandler.setBackgroundColor(Color.GRAY);
				builder.setTitle("Terminada Tarea pesada");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setMessage("Terminada Tarea Pesada");
				builder.setNeutralButton("OK", null);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				alertDialog.setCancelable(false);
				lblTexto.setText("Handler cambio el color a gris");
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}