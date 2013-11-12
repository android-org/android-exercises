package com.example.checkradiopractica;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private CheckBox cbMarcame;
	private TextView lblMensaje;
	private RadioGroup rgOpciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cbMarcame = (CheckBox)findViewById(R.id.ChkMarcame);

        cbMarcame.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	cbMarcame.setText("Checkbox marcado!");
		        }
		        else {
		        	cbMarcame.setText("Checkbox desmarcado!");
		        }
	        }
        });
        
        lblMensaje = (TextView)findViewById(R.id.LblSeleccion);
        rgOpciones = (RadioGroup)findViewById(R.id.gruporb);

        rgOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	lblMensaje.setText("ID opción seleccionada: " + checkedId);
	        }
        });
    }

    public void onRadioButtonClicked(View view)
    {
    	boolean checked = ((RadioButton) view).isChecked();

    	
    	switch (view.getId()){
    	case R.id.radio1:
    		if (checked)
    		{
    			cbMarcame.setText("Radio1 marcado!");
    			break;
    		}
    	case R.id.radio2:
    		if (checked)
    		{
    			cbMarcame.setText("Radio2 marcado!");
    			break;
    		}
    	}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
