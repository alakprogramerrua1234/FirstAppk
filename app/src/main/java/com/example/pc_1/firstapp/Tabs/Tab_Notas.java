package com.example.pc_1.firstapp.Tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.adapters.AdaptadorListMaterias;
import com.example.pc_1.firstapp.adapters.AdaptadorListNotas;
import com.example.pc_1.firstapp.atributosCursos.Curso;


public class Tab_Notas extends Fragment {

    View view;
    ListView listView2;
    AdaptadorListNotas adaptadorList2;

    public Tab_Notas(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_notas, container, false);

        listView2 = view.findViewById(R.id.ListViewid2);
        adaptadorList2 = new AdaptadorListNotas(getActivity());
        listView2.setAdapter(adaptadorList2);

        /*if(adaptadorList2.getCont()!=cont){
            cont++;
            adaptadorList2.notifyDataSetChanged();
        }*/
        return view;
    }


}
