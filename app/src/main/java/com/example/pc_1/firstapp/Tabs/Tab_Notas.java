package com.example.pc_1.firstapp.Tabs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.adapters.AdaptadorListMaterias;
import com.example.pc_1.firstapp.adapters.AdaptadorListNotas;
import com.example.pc_1.firstapp.atributosCursos.Comunicador;
import com.example.pc_1.firstapp.atributosCursos.Curso;


public class Tab_Notas extends Fragment {

    View view;
    ListView listView2;
    AdaptadorListNotas adaptadorList2;
    private Curso curso;

    public Tab_Notas(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_notas, container, false);

        curso = Comunicador.getObjeto();
        listView2 = view.findViewById(R.id.ListViewid2);
        adaptadorList2 = new AdaptadorListNotas(getActivity());
        listView2.setAdapter(adaptadorList2);
        registerForContextMenu(listView2);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,v.getId(),0,"Eliminar nota");
        menu.add(0,v.getId(),0,"Editar nota");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle()=="Eliminar nota"){
            curso.getMisnotas().remove(info.position);
            adaptadorList2.notifyDataSetChanged();
           return true;
        }else if(item.getTitle()=="Editar nota"){
            return true;
        }else return super.onContextItemSelected(item);

    }

}
