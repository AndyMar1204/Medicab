package com.andy.Medicab.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Photo  implements Serializable {
    private Long Id;
    private String nom;
    private  String url;
    private LocalDateTime dateCreated;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
