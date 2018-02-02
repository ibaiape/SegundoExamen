package com.example.dm2.segundoexamen;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Multimedia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner cmbOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);ArrayList<String> datosSpinner = new ArrayList<String>();
        setTitle(getString(R.string.titMultimedia));
        try {
            InputStream fraw = getResources().openRawResource(R.raw.animales);
            BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            while(linea!=null){
                datosSpinner.add(linea);
                linea = brin.readLine();
            }
            fraw.close();
            cmbOpciones =(Spinner) findViewById(R.id.cmbOpciones);
            ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, datosSpinner);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbOpciones.setAdapter(adaptador);
            cmbOpciones.setOnItemSelectedListener(this);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String text = cmbOpciones.getSelectedItem().toString();

        Log.d("a",text);

        if(pos>0){
            MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(text, "raw", getPackageName()));
            mp.start();
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void volver(View v){
        finish();
    }
}
