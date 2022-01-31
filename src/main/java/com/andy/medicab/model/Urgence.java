package com.andy.medicab.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Urgence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    @ManyToMany
    private List<Hopital> hopitals = new ArrayList<Hopital>();
}
