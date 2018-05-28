package com.example.pc_1.firstapp.atributosCursos;

import java.util.ArrayList;

public class Curso {
    private String titulo,subtitulo;
    private ArrayList<Double> misnotas = new ArrayList<>();
    private ArrayList<Double> misporcentajes = new ArrayList<>();
    private ArrayList<String> mistareas = new ArrayList<>();
    private ArrayList<String> fechas = new ArrayList<>();

    public Curso(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public ArrayList<Double> getMisnotas() {
        return misnotas;
    }

    public ArrayList<Double> getMisporcentajes() {
        return misporcentajes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setMisnotas(double miNota) {
        this.misnotas.add(miNota);
    }

    public void setMisporcentajes(double miPorcentaje) {
        this.misporcentajes.add(miPorcentaje);
    }

    public ArrayList<String> getMistareas() {
        return mistareas;
    }

    public void setMistareas(String mitarea) {
        this.mistareas.add(mitarea);
    }

    public ArrayList<String> getFechas() {
        return fechas;
    }

    public void setFechas(String fecha) {
        this.fechas.add(fecha);
    }
}
