package com.example.pc_1.firstapp.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pc_1.firstapp.Dialogos.Dialogo1;
import com.example.pc_1.firstapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Materias extends Fragment {

    public Materias() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Materias(ArrayList<String> arrayList) {
        itemsMaterias = arrayList;
    }

    View view;
    ListView listView;
    ArrayList<String> itemsMaterias;
    int longclick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_materias, container, false);

        //listView con su adaptador
        listView = view.findViewById(R.id.ListViewid);
        final ArrayAdapter ListViewAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,itemsMaterias);
        listView.setAdapter(ListViewAdapter);

        //eliminar materias del Listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;
                longclick = 1;

                AlertDialog.Builder eliminarDial = new AlertDialog.Builder(getActivity());
                eliminarDial.setTitle("Importante")
                            .setMessage("¿ Eliminar materia ?")
                            .setCancelable(false)
                            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {
                                    itemsMaterias.remove(posicion);
                                    ListViewAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {
                                }
                            });
                eliminarDial.show();
                return false;
            }
        });

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

                            itemsMaterias.add(nombreNuevaM.getText().toString());
                            ListViewAdapter.notifyDataSetChanged();
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
                    fragment_TyN materias = new fragment_TyN();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contenedor, materias);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }

}
