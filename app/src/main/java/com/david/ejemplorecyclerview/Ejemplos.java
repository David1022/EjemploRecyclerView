package com.david.ejemplorecyclerview;

import java.util.ArrayList;
import java.util.Vector;

public class Ejemplos {

    public static ArrayList<String> revisionesPendientes = new ArrayList<String>();
    public static ArrayList<String> revisionesEnCurso = new ArrayList<String>();
    public static ArrayList<String> revisionesFinalizadas = new ArrayList<String>();
    public static final int NUM_ITEMS = 40;

    public Ejemplos() {
    }

    public static ArrayList<String> getPendientes (){
        for (int i=0; i<NUM_ITEMS; i++) {
            revisionesPendientes.add("Pendientes " + i);
        }
        return revisionesPendientes;
    }

    public static ArrayList<String> getEnCurso (){
        for (int i=0; i<NUM_ITEMS; i++) {
            revisionesEnCurso.add("En curso " + i);
        }
        return revisionesEnCurso;
    }

    public static ArrayList<String> getFinalizadas (){
        for (int i=0; i<NUM_ITEMS; i++) {
            revisionesFinalizadas.add("Finalizadas " + i);
        }
        return revisionesFinalizadas;
    }



}
