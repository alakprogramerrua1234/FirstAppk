package com.example.pc_1.firstapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pc_1.firstapp.Tabs.Tab_Notas;
import com.example.pc_1.firstapp.Tabs.Tab_Tareas;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter (FragmentManager fragmentManager){
        super(fragmentManager);
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