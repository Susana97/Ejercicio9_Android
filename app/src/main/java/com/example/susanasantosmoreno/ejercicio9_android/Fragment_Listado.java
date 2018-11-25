package com.example.susanasantosmoreno.ejercicio9_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fragment_Listado extends Fragment {

    private static final int SECONDARY_ACTIVITY_TAG = 1;
    private InputStream inputInfo;
    private InputStream inputPeces;
    private BufferedReader br;
    private Spinner opcionesPeces;
    private ArrayAdapter<String> adaptador;
    private TextView textViewTitle;
    private AdaptadorInformacion adaptadorListPeces;
    private AdaptadorInformacion adaptadorListAlgas;
    private ListView listaOpciones;
    private DetalleListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        //SE CREAN LOS ARRAYS CON LA INFORMACIÓN OBTENIDA DE LOS FICHEROS.
        final ArrayList<InformacionAnimales> informacionPeces = leerInformacionFichero("peces");
        final ArrayList<InformacionAnimales> informacionAlgas = leerInformacionFichero("algaseinvertebrados");

        //USAMOS ADAPTADORES PARA DIBUJAR LAS OPCIONES DE LAS LISTAS.
        adaptadorListPeces = new AdaptadorInformacion(getActivity(), informacionPeces);
        adaptadorListAlgas = new AdaptadorInformacion(getActivity(), informacionAlgas);

        //CREAMOS Y INTRODUCIMOS LAS OPCIONES EN EL SPINNER.
        final String [] opciones = new String[]{"Peces", "Algas e Invertebrados"};
        adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opciones);
        opcionesPeces = (Spinner)getActivity().findViewById(R.id.spinnerPeces);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcionesPeces.setAdapter(adaptador);

        textViewTitle = (TextView)getActivity().findViewById(R.id.textViewTitulo);
        listaOpciones = (ListView)getActivity().findViewById(R.id.ListViewPrincipal);

        if (informacionPeces.size() > 0) {
            listener.onDetalleListener(informacionPeces.get(0), true);
        }

        //Acciones del Spiner.
        opcionesPeces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String delparque = getResources().getString(R.string.delparque);
                textViewTitle.setText((opciones[position]).toUpperCase() + " " + delparque);
                if(position == 0){//ESTAMOS EN LA OPCION DE PECES
                    listaOpciones.setAdapter(adaptadorListPeces);
                    listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //cuando se hace click se abre la segunda ventana.

                            listener.onDetalleListener(informacionPeces.get(position), false);
                        }
                    });
                    adaptador.notifyDataSetChanged();

                }else{//ESTAMOS EN LA OPCIÓN DE ALGAS.
                    listaOpciones.setAdapter(adaptadorListAlgas);
                    listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //cuando se hace click se abre la segunda ventana.

                            listener.onDetalleListener(informacionAlgas.get(position), false);
                        }
                    });
                    adaptador.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

    }

    public ArrayList<InformacionAnimales> leerInformacionFichero(String fichero){
        ArrayList<InformacionAnimales> informacion = new ArrayList<InformacionAnimales>();

        try{
            int resource = getResources().getIdentifier(fichero, "raw", getActivity().getPackageName());
            inputInfo = getResources().openRawResource(resource);
            br = new BufferedReader(new InputStreamReader(inputInfo));
            String texto = null;
            while((texto = br.readLine())!= null) {
                String[] lista = texto.split(",");
                //para parar el ID de la imagen
                int imagen = getResources().getIdentifier(lista[0], "drawable", getActivity().getPackageName());
                informacion.add(new InformacionAnimales(imagen, lista[1], lista[2], lista[3], lista[4]));
            }
        }catch (Exception e){
            e.printStackTrace();
            //Log.e("Ficheros", "Error al leer fichero desde recurso Raw");
        }finally {
            if(inputInfo != null){
                try{
                    inputInfo.close();
                }catch (IOException e){
                    Log.e("Ficheros", "Error al cerrar");
                }
            }
            if(br != null){
                try{
                    br.close();
                }catch (IOException e){
                    Log.e("Ficheros", "Error al cerrar");
                }
            }
        }
        return informacion;
    }

    public interface DetalleListener{
        void onDetalleListener(InformacionAnimales infoAnim, boolean primeraVez);
    }

    public void setDetallesListener(DetalleListener listener){
        this.listener = listener;
    }
}
