package com.andy.Medicab.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Urgence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private  Urgences typeUrgences;
    @Enumerated(EnumType.STRING)
    private Transport typeTransport;
    @ManyToOne
   private User user ;
    @ManyToOne
    private Hopital hopital;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private EmergencyState etat;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Urgences getTypeUrgences() {
        return typeUrgences;
    }

    public void setTypeUrgences(Urgences typeUrgences) {
        this.typeUrgences = typeUrgences;
    }


    public Hopital getHopital() {
        return hopital;
    }
    
    public void setHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Urgence() {
        setEtat(EmergencyState.ENCOURS);
        setDate(LocalDateTime.now());
    }

    public Transport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(Transport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmergencyState getEtat() {
        return etat;
    }

    public void setEtat(EmergencyState etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Urgence{" +
                "id=" + id +
                ", typeUrgences=" + typeUrgences +
                ", typeTransport=" + typeTransport +
                ", user=" + user.id +
                ", hopital=" + hopital.id +
                ", date=" + date +
                ", etat=" + etat +
                '}';
    }
}
