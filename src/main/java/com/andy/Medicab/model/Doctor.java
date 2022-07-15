package com.andy.Medicab.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ir Andy
 */
@Entity(name = "DOCTOR")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    private String postnom;
    private String hopital;
    private String telephone;
    private String email;
    private String fonction;
    private String adresse;
    @Lob
    private String biography;
    @OneToOne
    @JoinColumn(name = "profil_id")
    private FileInfo profil;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> patients = new ArrayList<>();

    public FileInfo getProfil() {
        return profil;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public void setProfil(FileInfo profil) {
        this.profil = profil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHopital() {
        return hopital;
    }

    public void setHopital(String hopital) {
        this.hopital = hopital;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<User> getPatients() {
        return patients;
    }

    public void addPatients(User patients) {
        this.patients.add(patients);
        patients.setDoctor(this);
    }
    public void removePatient(User user){
        this.patients.remove(user);
        user.setDoctor(null);
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
