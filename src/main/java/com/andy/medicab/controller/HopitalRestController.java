package com.andy.medicab.controller;

import com.andy.medicab.model.Hopital;
import com.andy.medicab.services.HopitalServiceImpl;
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
@RequestMapping("/rest/hopital")
public class HopitalRestController implements GenericCrud<Hopital> {
    @Autowired
    private HopitalServiceImpl service;


    /**
     * @param hopital
     * @return
     */
    @Override
    @PostMapping(path = "add")
    public ResponseEntity<Hopital> save(@RequestBody Hopital hopital) {
        Hopital hopital1 = service.save(hopital);
        if(hopital1 != null) return new ResponseEntity<Hopital>(hopital1, HttpStatus.OK);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param hopital
     * @return
     */
    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Hopital> update(@RequestBody Hopital hopital, Long id) {
        if (service.checkIfExist(hopital.getId())){
            Hopital hopital1 = service.getById(hopital.getId());
            hopital1.setAdresse(hopital.getAdresse());
            hopital1.setInfos(hopital.getInfos());
            hopital1.setNom(hopital.getNom());
            service.update(hopital1);
            return new ResponseEntity<Hopital>(hopital1,HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if(service.checkIfExist(id)){
            service.delete(service.getById(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @GetMapping(path = "getById/{id}")
    public ResponseEntity<Hopital> findById(@PathVariable long id) {
        Hopital hopital = service.getById(id);

        if(hopital != null){
            return new ResponseEntity<Hopital>(hopital, HttpStatus.FOUND);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping(path = "getAll")
    public ResponseEntity<List<Hopital>> getAll() {
        List<Hopital> hopitals = service.getAll();
        return new ResponseEntity<List<Hopital>>(hopitals,HttpStatus.OK);
    }
}
