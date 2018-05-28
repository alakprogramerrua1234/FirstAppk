package com.example.pc_1.firstapp.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.adapters.AdaptadorListNotas;
import com.example.pc_1.firstapp.adapters.AdaptadorListTareas;


public class Tab_Tareas extends Fragment {

    View view;
    ListView listView;
    AdaptadorListTareas adaptadorListTareas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_tareas, container, false);

        listView = view.findViewById(R.id.ListViewTareas);
        adaptadorListTareas = new AdaptadorListTareas(getActivity());
        listView.setAdapter(adaptadorListTareas);
        return view;
    }


}
