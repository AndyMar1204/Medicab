/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.controller;

import com.andy.medicab.model.Admin;
import com.andy.medicab.model.AdminType;
import com.andy.medicab.model.Adresse;
import com.andy.medicab.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Ir Andy
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/admin")
public class AdminRestController implements GenericCrud<Admin>{

    @Autowired
    private AdminServiceImpl adminDao;
    @RequestMapping("")
    public  String home(){
        return "Gestion des admins";
    }
    @GetMapping("getByNumberAndPassword/{number}/{password}")
    public ResponseEntity<Admin> loggin(@PathVariable String number, @PathVariable String password){
        Admin admin = adminDao.findByNumberAndPassword(number,password);
        if(admin == null)
            return  ResponseEntity.notFound().build();
        else
return new ResponseEntity<Admin>(admin, HttpStatus.OK);
}
@Autowired
public void setDefaultAdmin(){
        Admin admin = new Admin();
admin.setAdresse(new Adresse());
        admin.setNumber("0970634739");
        admin.setPassword("andyman");
        admin.setEmail("josueandy5@gmail.com");
        admin.setType(AdminType.SUPER_USER);
        admin.setUsername("andyman");
        adminDao.save(admin);
}


    @Override
    public ResponseEntity<Admin> save(Admin admin) {
        admin.setType(AdminType.DEFAULT);
        return null;
    }

    @Override
    public ResponseEntity<Admin> update(Admin admin,Long id) {
        return null;
    }

    @Override
    @DeleteMapping(path = "/deleteAdmin/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return null;
    }


    @Override
    @GetMapping(path = "/getAdminById/{id}")
    public ResponseEntity<Admin> findById(@PathVariable long id) {
        Admin admin = this.adminDao.getById(id);
        if(admin != null){
            return new ResponseEntity<Admin>(admin, HttpStatus.OK);
        }else return ResponseEntity.notFound().build();

    }

    @Override
    @GetMapping(path = "/getAllAdmin")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = this.adminDao.getAll();
        return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
    }
    @PostMapping(path = "/connexion")
    public ResponseEntity<Admin> connexion(@RequestBody Admin admin){
        Admin admin1 = this.adminDao.findByNumberAndPassword(admin.getNumber(),admin.getPassword());
        if (admin1==null) return  ResponseEntity.notFound().build();
        return new ResponseEntity<Admin>(admin1,HttpStatus.OK);
    }
}
