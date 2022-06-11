
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
    private String nom;
    private String postnom;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Doctor doctor;
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER)
    List<Urgence> urgences = new ArrayList<>();

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    @Override
    public String toString() {
        return "User{"  +" id : "+id+ "nom=" + nom + ", postnom=" + postnom + ", doctor=" + doctor + ", urgences=" + urgences + '}';
    }

   
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


}
