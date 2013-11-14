package com.example.sharedpreferencesexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	private SharedPreferences misPreferencias;

	private EditText txtUsuario;
	private EditText txtLevel;
	private EditText txtScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Referencia al manager de Preferences
		misPreferencias = getSharedPreferences("mis_preferencias",
				Context.MODE_PRIVATE);

		// Recuperando datos
		String usuario = misPreferencias.getString("usuario", "default-user");
		String level = misPreferencias.getString("level", "pantalla0");
		String  score = misPreferencias.getString("score", "0");

		// Actualizando EditTexts
		txtUsuario = (EditText) findViewById(R.id.TxtUsuario);
		txtLevel = (EditText) findViewById(R.id.TxtLevel);
		txtScore = (EditText) findViewById(R.id.TxtScore);

		txtUsuario.setText(usuario);
		txtLevel.setText(level);
		txtScore.setText(score);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Editor editor = misPreferencias.edit();
		editor.putString("usuario", txtUsuario.getText().toString());
		editor.putString("level", txtLevel.getText().toString());
		editor.putString("score", txtScore.getText().toString());
		editor.commit();

		super.onPause();
	}

}
