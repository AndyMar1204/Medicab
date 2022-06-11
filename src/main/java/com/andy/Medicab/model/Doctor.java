package com.andy.Medicab.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ir Andy
 */
@Entity(name = "DOCTOR")
public class Doctor  extends Account{
    private String nom;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<User> patients  = new ArrayList<>();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<User> getPatients() {
        return patients;
    }

    public void addPatient(User patient){
        patient.setDoctor(this);
        this.patients.add(patient);
    }

    public  void removePatient(User patient){
        this.patients.remove(patient);
        patient.setDoctor(null);
    }
}
