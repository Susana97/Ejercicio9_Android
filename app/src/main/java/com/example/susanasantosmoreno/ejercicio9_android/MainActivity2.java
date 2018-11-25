package com.example.susanasantosmoreno.ejercicio9_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView NombreImagen = (TextView)findViewById(R.id.textViewNombreImagen);
        NombreImagen.setVisibility(View.GONE);
        this.setTitle((getIntent().getStringExtra("titulo") + "(" +
                getIntent().getStringExtra("tituloLatin") + ")").toUpperCase());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Metodo para volver a la actividad principal si se pulsa la flecha.
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                Intent datos = new Intent();
                setResult(RESULT_CANCELED, datos);
                finish();
                return true;
            case R.id.action_settings:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoInfo dialogo = new DialogoInfo();
                dialogo.show(fragmentManager, "tagAlerta");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
