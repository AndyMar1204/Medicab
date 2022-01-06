
package com.andy.medicab.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.StringJoiner;

/**
 *
 * @author Ir Andy
 */


@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class User extends Account {
    private String nom;
   private String postnom;

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
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("nom='" + nom + "'")
                .add("postnom='" + postnom + "'")
                .add("id=" + id)
                .toString();
    }
}
