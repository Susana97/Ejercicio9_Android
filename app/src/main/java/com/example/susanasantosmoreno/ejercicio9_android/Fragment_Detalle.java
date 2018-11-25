package com.example.susanasantosmoreno.ejercicio9_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Detalle extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    public void mostrarImagen(int imagen, String nombreEsp, String nombreLat){
        TouchImageView imagenPrincipal = (TouchImageView)getView().findViewById(R.id.ImagenPrincipal);
        imagenPrincipal.setImageResource(imagen);
        String titulo = nombreEsp + "(" + nombreLat + ")";
        TextView tituloImagen = (TextView)getView().findViewById(R.id.textViewNombreImagen);
        tituloImagen.setText(titulo.toUpperCase());
    }
}
