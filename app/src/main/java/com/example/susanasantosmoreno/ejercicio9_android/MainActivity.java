package com.example.susanasantosmoreno.ejercicio9_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Fragment_Listado.DetalleListener {

    private final int SECONDARY_ACTIVITY_TAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment_Listado frgListado =
                (Fragment_Listado)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setDetallesListener(this);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }


    @Override
    public void onDetalleListener(InformacionAnimales infoAnim) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            ((Fragment_Detalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarImagen(infoAnim.getImagen(), infoAnim.getNombreComun(),
                    infoAnim.getNombreLatin());
        }
        else {
            Intent i = new Intent(this, Fragment_Detalle.class);
            i.putExtra("imagen", infoAnim.getImagen());
            i.putExtra("titulo", infoAnim.getNombreComun());
            i.putExtra("tituloLatin", infoAnim.getNombreLatin());
            i.putExtra("longitud", infoAnim.getLongitud());
            i.putExtra("habitat", infoAnim.getHabitat());
            startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
            //creamos el intent que lanza la actividad y le pasamos la imagen como
            //datos extra.
        }
    }
}
