package com.example.dm2.segundoexamen;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tiempo extends AppCompatActivity {
    private TextView txtTiempo;
    private TextView txtElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);
        txtTiempo = (TextView)findViewById(R.id.txtTiempo);
        txtElegido = (TextView)findViewById(R.id.txtElegido);
        setTitle(getString(R.string.btnTiempo));
    }

    public void datosBilbao(View v){
        cargarDatos("http://xml.tutiempo.net/xml/8050.xml");
        txtElegido.setText("Bilbo-Bilbao");
    }

    public void datosVitoria(View v){
        cargarDatos("http://xml.tutiempo.net/xml/8043.xml");
        txtElegido.setText("Vitoria-Gasteiz");
    }

    public void datosDonostia(View v){
        cargarDatos("http://xml.tutiempo.net/xml/4917.xml");
        txtElegido.setText("Donostia-San Sebastian");
    }

    public void volver(View v){
        finish();
    }

    private void cargarDatos(String url){
        Tiempo.CargarXmlTask tarea = new Tiempo.CargarXmlTask();
        tarea.execute(url);
    }

    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        String str;

        protected Boolean doInBackground(String... params) {
            RssParserDomTiempo saxparser = new RssParserDomTiempo(params[0]);
            str = saxparser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            txtTiempo.setText(""+str);
        }
    }
}
