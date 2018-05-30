package com.example.pc_1.firstapp.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.adapters.AdaptadorListNotas;
import com.example.pc_1.firstapp.adapters.AdaptadorListTareas;
import com.example.pc_1.firstapp.atributosCursos.Comunicador;
import com.example.pc_1.firstapp.atributosCursos.Curso;


public class Tab_Tareas extends Fragment {

    View view;
    ListView listView;
    AdaptadorListTareas adaptadorListTareas;
    private Curso curso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_tareas, container, false);
        curso = Comunicador.getObjeto();
        listView = view.findViewById(R.id.ListViewTareas);
        adaptadorListTareas = new AdaptadorListTareas(getActivity());
        listView.setAdapter(adaptadorListTareas);
        registerForContextMenu(listView);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,v.getId(),0,"Eliminar tarea");
        menu.add(0,v.getId(),0,"Editar tarea");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle()=="Eliminar tarea"){
            curso.getMisnotas().remove(info.position);
            adaptadorListTareas.notifyDataSetChanged();
            return true;
        }else if(item.getTitle()=="Editar tarea"){
            return true;
        }else return super.onContextItemSelected(item);

    }

}
