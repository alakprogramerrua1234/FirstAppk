package com.example.pc_1.firstapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pc_1.firstapp.Tabs.Tab_Notas;
import com.example.pc_1.firstapp.Tabs.Tab_Tareas;
import com.example.pc_1.firstapp.atributosCursos.Curso;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //private Curso curso;

    public ViewPagerAdapter (FragmentManager fragmentManager){
        super(fragmentManager);
        //this.curso = curso;
    }
    String[] tituloTabs = {"Tareas","Notas"};

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:return new Tab_Tareas();
            case 1:return new Tab_Notas();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloTabs[position];
    }
}