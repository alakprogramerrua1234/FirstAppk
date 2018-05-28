package com.example.pc_1.firstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pc_1.firstapp.R;
import com.example.pc_1.firstapp.atributosCursos.Curso;

import java.util.ArrayList;

public class AdaptadorListMaterias extends BaseAdapter {

    private Context context;
    private ArrayList<Curso> listaMaterias;

    public AdaptadorListMaterias(Context context, ArrayList<Curso> listaMaterias) {
        this.context = context;
        this.listaMaterias = listaMaterias;
    }

    @Override
    public int getCount() {
        if(listaMaterias!=null) {
            return listaMaterias.size();
        }else return 0;
    }

    @Override
    public Object getItem(int i) {
        return listaMaterias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Curso item = (Curso) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.list_materias,null);
        TextView titulo = view.findViewById(R.id.tituloid);
        TextView subtitulo = view.findViewById(R.id.subtituloid);

        titulo.setText(item.getTitulo());
        subtitulo.setText(item.getSubtitulo());
        return view;
    }
}
