package com.example.pc_1.firstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.atributosCursos.Comunicador;
import com.example.pc_1.firstapp.atributosCursos.Curso;

public class AdaptadorListTareas extends BaseAdapter {

    private Context context;
    private Curso curso;
    private int cont = 0;

    public AdaptadorListTareas(Context context) {
        this.context = context;
        curso = Comunicador.getObjeto();
    }
    @Override
    public int getCount() {
        if(curso!=null) {
            return curso.getMistareas().size();
        }else {
            return 0;}
    }

    @Override
    public Object getItem(int i) {
        return curso;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public int getCont() {
        return cont;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Curso item = (Curso) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.list_materias,null);
        TextView titulo = view.findViewById(R.id.tituloid);
        TextView subtitulo = view.findViewById(R.id.subtituloid);

        titulo.setText(item.getMistareas().get(cont));
        subtitulo.setText(item.getFechas().get(cont));
        cont++;

        return view;
    }
}
