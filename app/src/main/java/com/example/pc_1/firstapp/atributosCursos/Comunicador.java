package com.example.pc_1.firstapp.atributosCursos;

import java.util.ArrayList;

public class Comunicador {

    private static Curso objeto = null;

    public static void setObjeto(Curso newObjeto) {
        objeto = newObjeto;
    }

    public static Curso getObjeto() {
        return objeto;
    }

}
