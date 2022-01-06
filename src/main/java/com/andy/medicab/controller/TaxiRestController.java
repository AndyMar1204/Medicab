package com.andy.medicab.controller;

import com.andy.medicab.model.Adresse;
import com.andy.medicab.model.Position;
import com.andy.medicab.model.Taxi;
import com.andy.medicab.services.TaxiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/taxis")
public class TaxiRestController implements GenericCrud<Taxi>{
    @GetMapping(path = "")
    public String home() {
        return "Taxis rests";
    }
    @Autowired
    private TaxiServiceImpl service;
    /**
     * @param taxi
     * @return
     */
    @Override
    @PostMapping("add")
    public ResponseEntity<Taxi> save(@RequestBody Taxi taxi) {
        taxi = service.save(taxi);
        return new ResponseEntity<Taxi>(taxi, HttpStatus.OK);
    }

    /**
     * @param taxi
     * @return
     */
    @Override
    @PutMapping("update/{id}")
    public ResponseEntity<Taxi> update(@RequestBody Taxi taxi,@PathVariable Long id) {
        if (service.checkIfExist(id)){
            Taxi taxi1 = service.getById(id);
            taxi1.setPlaque(taxi.getPlaque());
            service.update(taxi1);
            return new ResponseEntity<Taxi>(taxi1,HttpStatus.OK);
        }return ResponseEntity.notFound().build();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("update/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (service.checkIfExist(id)){
            Taxi taxi = service.getById(id);
            service.delete(taxi);
            return ResponseEntity.ok().build();
        }return ResponseEntity.notFound().build();

    }

    /**
     * @param id
     * @return
     */
    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<Taxi> findById(@PathVariable long id) {
        Taxi taxi = service.getById(id);
        if(taxi==null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Taxi>(taxi,HttpStatus.OK);
    }

    @Override
    @GetMapping("getAll")
    public ResponseEntity<List<Taxi>> getAll() {
        List<Taxi> taxis = service.getAll();
        if(taxis==null)
            return ResponseEntity.notFound().build();
        else return new ResponseEntity<List<Taxi>>(taxis,HttpStatus.OK);
    }

    private void taxiGenerator(){
        for (int i = 0; i < 10; i++) {
            Taxi taxi = new Taxi();
            Adresse adresse = new Adresse();
            adresse.setCommune("Kalamu");
            taxi.setPlaque(String.valueOf(new Random().doubles(4).findAny().getAsDouble()*10000).substring(0,4).replace('.','0'));
            taxi.setAdresse(adresse);
            taxi.setEmail("jos@gmail");
            taxi.setNumber(String.valueOf(new Random().nextDouble()*1000000000).substring(0,10).replace('.','0'));
            Position position = new Position();
            position.setLongitude(new Random().nextDouble()*40000);
           position.setLatitude(new Random().nextDouble()*40000);
           taxi.setPosition(position);
            service.save(taxi);
        }

    }
}
