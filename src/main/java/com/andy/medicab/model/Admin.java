package com.andy.medicab.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Ir Andy
 */
@Entity
public class Admin extends User{
    @Enumerated(EnumType.STRING)
    private AdminType type;

    public AdminType getType() {
        return type;
    }

    public void setType(AdminType type) {
        this.type = type;
    }
}
