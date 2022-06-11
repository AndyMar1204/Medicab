package com.andy.Medicab.controller;

import com.andy.Medicab.model.Driver;
import com.andy.Medicab.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/rest/driver/")
public class DriverController implements Crud<Driver, Long> {
    @Autowired
    private DriverService service;

    @PostMapping(path = SAVE_)
    @Override
    public ResponseEntity<Long> save(@RequestBody Driver driver) {
        try {
        	driver.setPassword(encryptPassword(driver.getPassword()));
            return new ResponseEntity<Long>(service.save(driver), HttpStatus.CREATED);
        }catch (Exception ex){
            if(ex instanceof DataIntegrityViolationException)
                return buildErrorMessage(ex, "email ou numero deja utilisé par un autre compte");
            return buildErrorMessage(ex,"Erreur lors du sauvegarde du driver");
        }
    }

    @PutMapping(path = UPDATE_ + "{id}")
    @Override
    public ResponseEntity<Driver> update(@RequestBody Driver driver, @PathVariable Long id) {
        try {
            driver.setId(id);
            return new ResponseEntity<Driver>(service.update(driver),HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Echec de la mise à jour du driver");
        }
    }

    @DeleteMapping(path = DELETE_BY_ID + "{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return buildSuccessMessage("Driver effacé avec succes");
        }catch (Exception e){
            return buildErrorMessage(e ,"Echec de la suppression");
        }
    }

    @GetMapping(path = FIND_BY_ID + "{id}")
    @Override
    public ResponseEntity<Driver> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Driver>(service.findById(id),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Aucun driver trouvé");
        }
    }

    @GetMapping(path = FIND_ALL)
    @Override
    public ResponseEntity<List<Driver>> getAll() {
        try {
            return new ResponseEntity<List<Driver>>(service.findAll(),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Une erreur s'est produite");
        }
    }

    @GetMapping(path = CHECK_EXIST_BY_ID + "{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        try {
            return new ResponseEntity<Boolean>(service.existById(id),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Une erreur s'est produite");
        }
    }
    @PutMapping(path = "updateInfo")
    public ResponseEntity<Driver> updateInfo(@RequestBody Driver driver){
    	if(service.existById(driver.getId())) {
    		try {
    			Driver d = service.findById(driver.getId());
    			d.setPlaque(driver.getPlaque());
    			d.setUsername(driver.getUsername());
    			return new ResponseEntity<Driver>(service.update(d), HttpStatus.OK);
    		}catch(Exception ex) {
    			return buildErrorMessage(ex,"Impossible de sauvegarder cette mise à jour");
    		}
    	}else
    		return buildErrorMessage("Echec de mise à jour, compte introuvable");
    }
}
