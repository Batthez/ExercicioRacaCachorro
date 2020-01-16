package com.example.identificadorderaca.classes;

import java.util.ArrayList;

public class Raca {

    private String nomeRaca;
    private ArrayList<String> subRacas;

    public String getNomeRaca() {
        return nomeRaca;
    }

    public ArrayList<String> getSubRacas() {
        return subRacas;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public void setSub_racas(ArrayList<String> subRacas) {
        this.subRacas = subRacas;
    }
}
