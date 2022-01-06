/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.services.interfaces;

import com.andy.medicab.model.Admin;

/**
 *
 * @author Ir Andy
 */
public interface IAdminService extends GenericService<Admin> {
      /**
     *
     * @param telephone
     * @param password
     * @return
     */
    public Admin connexion(String telephone, String password);
}
