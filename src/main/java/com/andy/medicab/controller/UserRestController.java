package com.andy.medicab.controller;

import com.andy.medicab.model.Position;
import com.andy.medicab.model.User;
import com.andy.medicab.services.UserServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ir Andy
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping(path = "")
    public String home() {
        return "Users rest";
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<User>> getAll() {
        //saveUsers();
        List<User> users = service.getAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getById(id);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        //Long id  = user.getId();

        if (service.checkIfExist(user.getId())) {
            User us = service.getById(user.getId());
            us.setUsername(user.getUsername());
            us.setPassword(user.getPassword());
            us.setEmail(user.getEmail());
            us.setNumber(user.getNumber());
            us = service.update(us);
            return new ResponseEntity<User>(us, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setPassword(Outils.passwordHasher(user.getPassword()));

        User us = service.save(user);
        if (us == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<User>(us, HttpStatus.CREATED);
    }

    @PostMapping("signIn")
    public ResponseEntity<User> signin(@RequestBody User user) {
        if (user.getPassword() != null) {
            user.setPassword(Outils.passwordHasher(user.getPassword()));
        }
        System.out.println(user.getNumber() + " et " + user.getPassword());
        User us = service.connexion(user.getNumber(), user.getPassword());
        System.out.println("l'id est " + us.getNumber() + " et " + user.getPassword());
        if (us == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<User>(us, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (service.checkIfExist(id)) {
            service.delete(service.getById(id));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateIdentity/{id}")
    public ResponseEntity<User> updateIdentity(@PathVariable Long id, @RequestBody User user) {
        if (service.checkIfExist(id)) {
            User us = service.getById(id);
            us.setUsername(user.getUsername());
            us.setEmail(user.getEmail());
            us.setNom(user.getNom());
            us.setPostnom(user.getPostnom());
            service.update(us);
            return new ResponseEntity<User>(us, HttpStatus.OK);
        } else
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/updatePosition/{id}")
    public ResponseEntity<User> updatePosition(@PathVariable Long id, @RequestBody Position position){
        if(this.service.checkIfExist(id)){
            User user = service.getById(id);
            System.out.println(position);
            user.setPosition(position);
            user = service.update(user);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }else
            return ResponseEntity.noContent().build();
    }
    private void saveUsers() {
        List<User> users = new ArrayList();
        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setEmail("email " + i);
            user.setNumber("number " + i);
            user.setPassword(Outils.passwordHasher("password " + i));
            user.setUsername("usename " + i);
            users.add(user);
        }
        users.forEach(use -> {
            service.save(use);
        });
    }
}
