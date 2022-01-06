/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.model;

/**
 *
 * @author Ir Andy
 */
public enum AdminType {

    DEFAULT(0),WAPIMED(1),MEDICAB(3),SUPER_USER(5);
    private int niveau;
    AdminType(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
