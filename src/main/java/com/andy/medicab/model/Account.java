
package com.andy.medicab.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;


/**
 * @author Ir Andy
 */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    private String username;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade={CascadeType.ALL})
    private Position position;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    @OneToOne(cascade={CascadeType.ALL})
    private Adresse adresse;


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Account(

    ) {
        this.setPosition(new Position());
        this.setAdresse(new Adresse());
        this.setDateCreated(LocalDateTime.now());
    }

    @Override
    public String toString() {

        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
