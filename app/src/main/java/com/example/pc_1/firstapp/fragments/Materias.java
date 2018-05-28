package com.example.pc_1.firstapp.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.adapters.AdaptadorListMaterias;
import com.example.pc_1.firstapp.atributosCursos.Comunicador;
import com.example.pc_1.firstapp.atributosCursos.Curso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Materias extends Fragment {

    public Materias() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    AdaptadorListMaterias adaptadorListMaterias;
    ArrayList<Curso> itemsMaterias;
    int longclick;

    @SuppressLint("ValidFragment")
    public Materias(ArrayList<Curso> arrayList) {
        itemsMaterias = arrayList;
    }

    //list_notas.XML,list_tareas.XML

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_materias, container, false);

        //listView con su adaptador
        listView = view.findViewById(R.id.ListViewid);
        adaptadorListMaterias = new AdaptadorListMaterias(getActivity(),itemsMaterias);
        listView.setAdapter(adaptadorListMaterias);
        registerForContextMenu(listView);  //menu contextual flotante

        //boton flotante para agregar materias
        FloatingActionButton fab = view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View dView = layoutInflater.inflate(R.layout.dialogo_materias,null);  //necesitamos este view para inflar el dialogo con su nuevo layout(XML)
                final EditText nombreNuevaM = dView.findViewById(R.id.nombre);
                final EditText numCreditos = dView.findViewById(R.id.creditos);
                builder.setTitle("Agregar materia:")
                        .setCancelable(true)
                        .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            itemsMaterias.add(new Curso(nombreNuevaM.getText().toString(),numCreditos.getText().toString()+" creditos"));
                            adaptadorListMaterias.notifyDataSetChanged();
                        }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                builder.setView(dView);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        //listener para pasar de una materia a tareas y notas
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(longclick!=1) {
                    Comunicador.setObjeto(itemsMaterias.get(i));
                    fragment_TyN materias = new fragment_TyN();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor, materias);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else longclick = 0;
            }
        });

        return view;
    }

    //aqui se crea el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,v.getId(),0,"Agregar nota");
        menu.add(0,v.getId(),0,"Agregar tarea");
        menu.add(0,v.getId(),0,"Eliminar materia");
    }

    //esta funcion se llama cuando una opcion es seleccionada
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle()=="Agregar nota"){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View mView = inflater.inflate(R.layout.dialogo_agregarnotas,null);  //necesitamos este view para inflar el dialogo con su nuevo layout(XML)
            final EditText nuevaNota = mView.findViewById(R.id.notaId);
            final EditText nuevoPorcentaje = mView.findViewById(R.id.porcentajeId);
            builder.setTitle("Agregar Nota:")
                    .setCancelable(true)
                    .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            itemsMaterias.get(info.position).setMisnotas(Double.parseDouble(nuevaNota.getText().toString()));
                            itemsMaterias.get(info.position).setMisporcentajes(Double.parseDouble(nuevoPorcentaje.getText().toString()));
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            builder.setView(mView);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }else if(item.getTitle()=="Agregar tarea"){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View mView = inflater.inflate(R.layout.dialogo_materias,null);  //necesitamos este view para inflar el dialogo con su nuevo layout(XML)
            final EditText nuevaTarea = mView.findViewById(R.id.nombre);
            final EditText fecha = mView.findViewById(R.id.creditos);
            builder.setTitle("Agregar Tarea:")
                    .setCancelable(true)
                    .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            itemsMaterias.get(info.position).setMistareas(nuevaTarea.getText().toString());
                            itemsMaterias.get(info.position).setFechas(fecha.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            builder.setView(mView);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }else if(item.getTitle()=="Eliminar materia"){
            itemsMaterias.remove(info.position);
            adaptadorListMaterias.notifyDataSetChanged();
            return true;
        }else return super.onContextItemSelected(item);

    }
}
