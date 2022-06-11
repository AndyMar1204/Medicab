package com.andy.Medicab.model;

public enum Urgences {
    Grossesse("grossesse"),Accident("accident"),Evanouissement("evanouissement"),Attaque_Cardiaque("attaque cardiaque"),
    Empoisonnement("empoisonnement"),Covid_19("covid 19"),Blessure("blessure"),Chock("chock"),Probleme_dentaire("probleme dentaire"),Paralysie("paralysie");
    private String description;


    Urgences(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
