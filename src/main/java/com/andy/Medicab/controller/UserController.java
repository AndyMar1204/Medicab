package com.andy.Medicab.controller;

import com.andy.Medicab.model.User;
import com.andy.Medicab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.andy.Medicab.controller.Outils.*;


/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/users/")
public class UserController implements Crud<User,Long>{
    @Autowired
    private UserService service;

    @RequestMapping("")
    public String home() {
        return "API pour les utilisateurs";
    }
    @PostMapping(path = SAVE_)
    @Override
    public ResponseEntity<Long> save(@RequestBody User user) {
        try {
        	user.setPassword(encryptPassword(user.getPassword()));
            Long id = service.save(user);
            return  new ResponseEntity<Long>(id, HttpStatus.CREATED);
        }catch (Exception e){
            //e.printStackTrace();
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");

            return buildErrorMessage(e,"Echec d'enregistrement, \n Reessayez plus tard");
        }
    }
    @PutMapping(path = UPDATE_+"{id}")
    @Override
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable Long id) {
        try {
            user.setId(id);
            User user1 = service.update(user);
            return  new ResponseEntity<User>(user1, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Impossible de mettre à jour cet utilisateur");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping(path = DELETE_BY_ID+"{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(SUCCES,"Utilisateur effacé avec success");
            return  new ResponseEntity<Void>(headers,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Impossible d'effacer cet utilisateur");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }

    }
    @GetMapping(FIND_BY_ID+"¨{id}")
    @Override
    public ResponseEntity<User> findById(@PathVariable Long id) {
        try {
            User user= service.findById(id);
            return new ResponseEntity<User>(user,HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Aucun utilisateur trouvé");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }

    }
    @GetMapping(FIND_ALL)
    @Override
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<List<User>>(service.findAll(),HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Aucun utilisateur trouvé");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }
    }
    @GetMapping(CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        try {
            return new ResponseEntity<Boolean>(service.existById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add(ERROR,"Une erreur est survenue lors du traitement");
            return new ResponseEntity<>(null,headers,HttpStatus.CONFLICT);
        }
    }
    @PostMapping(path="updateInfo/{id}")
    ResponseEntity<User> updateUserInfo(@PathVariable Long id,@RequestBody User user){
    	if(service.existById(id)) {
    		try {
    			User us = service.findById(id);
        		us.setNom(user.getNom());
        		us.setPostnom(user.getPostnom());
        		return new ResponseEntity<User>(service.update(us),HttpStatus.OK);
    		}catch(Exception ex) {
    			return buildErrorMessage(ex, "Echec");
    		}
    		
    		
    	}else
    	return buildErrorMessage("Aucun utilisateur à modifier");
    }
}
