package com.andy.Medicab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "files_dir")
public class FileInfo implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FileInfo() {
    }

    public FileInfo(String filename, String url) {
        setName(filename);
        setUrl(url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
