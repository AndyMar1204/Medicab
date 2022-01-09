package com.andy.medicab.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double longitude;
    private Double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public Position() {
        this.setLongitude(Long.valueOf(0));
        this.setLatitude(Long.valueOf(0));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
