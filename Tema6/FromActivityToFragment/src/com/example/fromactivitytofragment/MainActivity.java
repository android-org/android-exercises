package com.example.fromactivitytofragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void navega(View v) {
    	EditText edit = (EditText) findViewById(R.id.editText1);
    	Intent a = new Intent(this,FutureFragment.class);
    	a.putExtra(EXTRA_URL, edit.getText().toString());
    	startActivity(a);
    }
    
}
