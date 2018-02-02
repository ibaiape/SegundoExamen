package com.example.dm2.segundoexamen;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Atomicos extends AppCompatActivity {

    private EditText elemento;
    private TextView txtSimbolo;
    private TextView txtNumero;
    private TextView txtPeso;
    private TextView txtPunto;
    private TextView txtDensidad;
    String simbolo = "";
    String numero = "";
    String peso = "";
    String punto = "";
    String densidad = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atomicos);
        elemento =(EditText)findViewById(R.id.txtElemento);
        txtSimbolo =(TextView)findViewById(R.id.txtSimbolo);
        txtNumero =(TextView)findViewById(R.id.txtNumero);
        txtPeso =(TextView)findViewById(R.id.txtPeso);
        txtPunto =(TextView)findViewById(R.id.txtPunto);
        txtDensidad =(TextView)findViewById(R.id.txtDensidad);
        setTitle(getString(R.string.btnAtomico));
    }

    public void datosElemento(View v){
        String elem = elemento.getText().toString();
        AsyncPost task = new AsyncPost();
        task.execute(elem);
    }

    private class AsyncPost extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                HttpURLConnection conn;
                URL url = new URL("http://www.webserviceX.NET/periodictable.asmx/GetAtomicNumber");
                String param ="ElementName="+ URLEncoder.encode(params[0],"UTF-8");
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                String result ="";
                Scanner inStream = new Scanner(conn.getInputStream());

                while (inStream.hasNextLine()) {
                    result = inStream.nextLine();
                    result = result.replaceAll("\\s+","");
                    if(result.startsWith("&lt;AtomicNumber&gt;"))
                        numero = result.replace("&lt;AtomicNumber&gt;", "").replace("&lt;/AtomicNumber&gt;", "");
                    if(result.startsWith("&lt;Symbol&gt;"))
                        simbolo = result.replace("&lt;Symbol&gt;", "").replace("&lt;/Symbol&gt;", "");
                    if(result.startsWith("&lt;AtomicWeight&gt;"))
                        peso = result.replace("&lt;AtomicWeight&gt;", "").replace("&lt;/AtomicWeight&gt;", "");
                    if(result.startsWith("&lt;BoilingPoint&gt;"))
                        punto = result.replace("&lt;BoilingPoint&gt;", "").replace("&lt;/BoilingPoint&gt;", "");
                    if(result.startsWith("&lt;Density&gt;"))
                        densidad = result.replace("&lt;Density&gt;", "").replace("&lt;/Density&gt;", "");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            txtNumero.setText(numero);
            txtSimbolo.setText(simbolo);
            txtPeso.setText(peso);
            txtPunto.setText(punto);
            txtDensidad.setText(densidad);
        }
    }

    public void volver(View v){
        finish();
    }

}
