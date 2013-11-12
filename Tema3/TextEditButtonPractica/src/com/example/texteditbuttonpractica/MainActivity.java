package com.example.texteditbuttonpractica;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView lblTexto;
	private EditText txtTexto;
	private Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTexto = (TextView) findViewById(R.id.LblEtiqueta);
		txtTexto = (EditText) findViewById(R.id.TxtTexto);
		btn1 = (Button) findViewById(R.id.Button);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {

				lblTexto.setText(txtTexto.getText().toString());
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
