package com.example.dm2.segundoexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));
    }

    public void ejercicio1(View v){
        Intent Ejer1 = new Intent(MainActivity.this, Tiempo.class);
        startActivity(Ejer1);
    }

    public void ejercicio2(View v){
        Intent Ejer2 = new Intent(MainActivity.this, Atomicos.class);
        startActivity(Ejer2);
    }

    public void ejercicio3(View v){
        Intent Ejer3 = new Intent(MainActivity.this, Multimedia.class);
        startActivity(Ejer3);
    }

    public void exit(View v){
        finish();
    }

}
