package com.andy.Medicab.controller;

import com.andy.Medicab.model.Account;
import com.andy.Medicab.model.Adresse;
import com.andy.Medicab.services.Accountservice;
import com.andy.Medicab.services.AdresseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import  static  com.andy.Medicab.controller.Outils.*;
@CrossOrigin(BASE_URL)
@RestController
@RequestMapping("/rest/account/")
public class AccountController implements Crud<Account, Long> {
    @Autowired
    private Accountservice accountservice;
    
    @Autowired
    private AdresseService adresseService;
    @PostMapping(path = SAVE_)
    @Override
    public ResponseEntity<Long> save(@RequestBody Account account) {
        try {
        	account.setPassword(encryptPassword(account.getPassword()));
            return new ResponseEntity<Long>(accountservice.save(account), HttpStatus.CREATED);
        }catch (Exception e){
            if(e instanceof DataIntegrityViolationException)
                return buildErrorMessage(e, "email ou numero deja utilisé par un autre compte");
            return  buildErrorMessage(e, "Une erreur est survenu lors de l'enregistrement");
        }
    }
    @PutMapping(path = UPDATE_+"{id}")
    @Override
    public ResponseEntity<Account> update(@RequestBody Account account,@PathVariable Long id) {
        try {
            account.setId(id);
            return new ResponseEntity<Account>(accountservice.update(account),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  buildErrorMessage(e,"Echec de mise à jour du compte");
        }
    }
    @DeleteMapping(path = DELETE_BY_ID+"{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            accountservice.deleteById(id);
            return buildSuccessMessage("Urgence effacée avec succes");
        }catch (Exception e){
            return buildErrorMessage(e, "impossible d'effacer l'urgence");
        }
    }
    @GetMapping(path = FIND_BY_ID+"{id}")
    @Override
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Account>(accountservice.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return  buildErrorMessage(e,"compte introuvable");
        }
    }
    @GetMapping(path = FIND_ALL)
    @Override
    public ResponseEntity<List<Account>> getAll() {
        try {
            return new ResponseEntity<List<Account>>(accountservice.findAll(),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Une erreur s'est produite");
        }
    }
    @GetMapping(CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(Long id) {
        try {
            return new ResponseEntity<Boolean>(accountservice.existById(id),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Une erreur est survenue lors du traitement");
        }
    }
    @PostMapping(path="updateAdresse/"+"{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse adresse){
    	if(this.adresseService.existById(id)) {
    		adresse.setId(id);
    		try {
    			Adresse ad = adresseService.update(adresse);
        		return new ResponseEntity<Adresse>(ad,HttpStatus.OK);
    		}catch(Exception ex) {
    			return buildErrorMessage(ex, "Erreur survenue lors de la mise à jour");
    		}
    		
    	}else
    		return buildErrorMessage("Adresse introuvable");
    }

}
