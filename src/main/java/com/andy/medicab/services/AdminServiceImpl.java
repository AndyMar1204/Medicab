/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.services;

import com.andy.medicab.dao.IAdminDao;
import com.andy.medicab.model.Admin;
import com.andy.medicab.services.interfaces.IAdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ir Andy
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminDao dao;
    @Override
    public Admin save(Admin t) {
        return dao.save(t);
    }

    @Override
    public Admin update(Admin t) {
        return dao.save(t);
    }

    @Override
    public void delete(Admin t) {
        dao.delete(t);
    }

    @Override
    public Admin getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public List<Admin> getAll() {
       return dao.findAll();
    }

    @Override
    public boolean checkIfExist(long id) {
        return dao.existsById(id);
    }

    @Override
    public Admin connexion(String telephone, String password) {
        return null;
    }
    public Admin findByNumberAndPassword(String number, String password){
        return dao.findByNumberAndPassword(number,password);
    }
}
