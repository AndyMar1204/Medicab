package com.andy.medicab.model;

import javax.persistence.Entity;

@Entity(name = "Hopital")
public class Hopital extends Account{
    private String nom;

    private String infos;


    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

}
