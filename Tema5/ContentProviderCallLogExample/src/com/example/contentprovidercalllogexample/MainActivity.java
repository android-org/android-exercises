package com.example.contentprovidercalllogexample;

import java.util.Calendar;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

//Deprecated
//Cursor c = managedQuery(llamadas, null, null, null, null);
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] TIPO_LLAMADA = { "", "entrante", "saliente", "perdida", "extraS2", "extrasS21" };
		//S2 special Calls.TYPE = 5
		TextView salida = (TextView) findViewById(R.id.salida);

		//Query Call Log
		Uri llamadas = Uri.parse("content://call_log/calls");
		Cursor c = getContentResolver().query(llamadas, null, null, null, null);
		while (c.moveToNext()) {
			//Log.d("CallLog" , "TYPE: "+ c.getString(c.getColumnIndex(Calls.TYPE)));		
			salida.append("\n"
					+ DateFormat.format("dd/MM/yy k:mm (",
							c.getLong(c.getColumnIndex(Calls.DATE)))
					+ c.getString(c.getColumnIndex(Calls.DURATION))
					+ ") "
					+ c.getString(c.getColumnIndex(Calls.NUMBER))
					+ ", "
					+ TIPO_LLAMADA[Integer.parseInt( c.getString(c.getColumnIndex(Calls.TYPE) ))]);

		}

		
		// Query usando Cursor con Filtrado: que devuelve las Llamadas posteriores a una
		// fecha
		String[] strFields = { android.provider.CallLog.Calls.NUMBER,
				android.provider.CallLog.Calls.TYPE,
				android.provider.CallLog.Calls.CACHED_NAME,
				android.provider.CallLog.Calls.DATE,
				android.provider.CallLog.Calls.DURATION };
		// Defines a string to contain the selection clause
		String mSelectionClause = android.provider.CallLog.Calls.DATE + " >= ?";
		// Initializes an array to contain selection arguments
		String[] mSelectionArgs = { createDate(2013, 1, 1).toString() };
		Cursor mCallCursor = getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, strFields,
				mSelectionClause, mSelectionArgs, null);

		// Insert de un registro en CallLog
		insertCallLog();

		// BORRANDO un registro en CallLog
		//deleteCallLog();

		// ACTUALIZANDO registros en CallLog
		updateCallLog();

	}

	public void insertCallLog() {
		ContentValues valores = new ContentValues();
		valores.put(Calls.DATE, new Date().getTime());
		valores.put(Calls.NUMBER, "650908978");
		valores.put(Calls.DURATION, "67");
		valores.put(Calls.TYPE, Calls.INCOMING_TYPE);

		getContentResolver().insert(Calls.CONTENT_URI,
				valores);
	}

	public void deleteCallLog() {
		String mSelectionClause = android.provider.CallLog.Calls.NUMBER
				+ " = '650908978'";
		getContentResolver().delete(Calls.CONTENT_URI, mSelectionClause, null);

	}

	public void updateCallLog() {
		ContentValues valores = new ContentValues();
		valores.put(Calls.DURATION, "100");
		String mSelectionClause = android.provider.CallLog.Calls.NUMBER	+ " = '650908978'";
		getContentResolver().update(Calls.CONTENT_URI, valores, mSelectionClause, null);

	}

	public static Long createDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTimeInMillis();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
