package com.example.susanasantosmoreno.ejercicio9_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Fragment_Listado.DetalleListener {

    private final int SECONDARY_ACTIVITY_TAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //poner el icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        Fragment_Listado frgListado =
                (Fragment_Listado)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setDetallesListener(this);
    }


    @Override
    public void onDetalleListener(InformacionAnimales infoAnim, boolean primeraVez) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            ((Fragment_Detalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarImagen(infoAnim.getImagen(), infoAnim.getNombreComun(),
                    infoAnim.getNombreLatin());
        }
        else {
            if (!primeraVez) {
                Intent i = new Intent(this, MainActivity2.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoInfo dialogo = new DialogoInfo();
                dialogo.show(fragmentManager, "tagAlerta");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
