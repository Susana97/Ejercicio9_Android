package com.example.susanasantosmoreno.ejercicio9_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Fragment_Detalle frgDetalles =
                (Fragment_Detalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);

        frgDetalles.mostrarImagen(getIntent().getIntExtra("imagen", 0),
                getIntent().getStringExtra("titulo"),
                getIntent().getStringExtra("tituloLatin"));
    }
}
