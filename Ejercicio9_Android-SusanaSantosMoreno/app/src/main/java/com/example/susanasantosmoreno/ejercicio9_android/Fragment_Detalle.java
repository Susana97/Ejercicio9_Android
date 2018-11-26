package com.example.susanasantosmoreno.ejercicio9_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Detalle extends Fragment {

    private final static String ESTADO_IMAGEN = "ESTADO_IMAGEN";
    private final static String ESTADO_NOMBRE = "ESTADO_NOMBRE";
    private final static String ESTADO_NOMBRELATIN = "ESTADO_NOMBRELATIN";
    private String nombre;
    private String nombreLatin;
    private String titulo;
    private int imagen;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        if(state != null){
            nombre = state.getString(ESTADO_NOMBRE);
            nombreLatin = state.getString(ESTADO_NOMBRELATIN);
            imagen = state.getInt(ESTADO_IMAGEN);
            mostrarImagen(imagen, nombre, nombreLatin);
        }
    }

    public void mostrarImagen(int imagen, String nombreEsp, String nombreLat){
        TouchImageView imagenPrincipal = (TouchImageView)getView().findViewById(R.id.ImagenPrincipal);
        this.imagen = imagen;
        this.nombre = nombreEsp;
        this.nombreLatin = nombreLat;
        imagenPrincipal.setImageResource(imagen);
        titulo = nombreEsp + "(" + nombreLat + ")";
        TextView tituloImagen = (TextView)getView().findViewById(R.id.textViewNombreImagen);
        tituloImagen.setText(titulo.toUpperCase());
    }

    @Override
    public void onSaveInstanceState(Bundle outInstance){
        super.onSaveInstanceState(outInstance);
        outInstance.putInt(ESTADO_IMAGEN, imagen);
        outInstance.putString(ESTADO_NOMBRE, nombre);
        outInstance.putString(ESTADO_NOMBRELATIN, nombreLatin);
    }
}
