
package com.andy.Medicab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Ir Andy
 */


@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class User extends Account {

    private String postnom;
    private String sexe;
    private String groupeSanguin;
    private  String alergies;
    private String donneurOrgane;

    @Lob
    private String traitement;

    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Urgence> urgences = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "hopital_id")
    private Hopital hopital;
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    public Hopital getHopital() {
        return hopital;
    }

    public void setHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public List<Urgence> getUrgences() {
        return urgences;
    }

    public void addUrgence(Urgence urgence) {
        urgences.add(urgence);
        urgence.setUser(this);
    }

    public void removeUrgence(Urgence urgence) {
        urgences.remove(urgence);
        urgence.setUser(null);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public String getAlergies() {
        return alergies;
    }

    public void setAlergies(String alergies) {
        this.alergies = alergies;
    }

    public String getDonneurOrgane() {
        return donneurOrgane;
    }

    public void setDonneurOrgane(String donneurOrgane) {
        this.donneurOrgane = donneurOrgane;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }
}
