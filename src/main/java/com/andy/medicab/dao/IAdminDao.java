/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.dao;

import com.andy.medicab.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ir Andy
 */
@Repository
public interface IAdminDao extends JpaRepository<Admin,Long>{
    @Query("SELECT ad FROM Admin ad WHERE ad.number = ?1 AND  ad.password = ?2")
    public Admin findByNumberAndPassword(String number, String password);
}
