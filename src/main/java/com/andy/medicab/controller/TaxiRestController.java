package com.andy.medicab.controller;

import com.andy.medicab.model.Adresse;
import com.andy.medicab.model.Position;
import com.andy.medicab.model.Driver;
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
public class TaxiRestController implements GenericCrud<Driver> {
    @GetMapping(path = "")
    public String home() {
        return "Taxis rests";
    }
    @Autowired
    private TaxiServiceImpl service;
    /**
     * @param driver
     * @return
     */
    @Override
    @PostMapping("add")
    public ResponseEntity<Driver> save(@RequestBody Driver driver) {
        driver = service.save(driver);
        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

    /**
     * @param driver
     * @return
     */
    @Override
    @PutMapping("update/{id}")
    public ResponseEntity<Driver> update(@RequestBody Driver driver, @PathVariable Long id) {
        if (service.checkIfExist(id)){
            Driver driver1 = service.getById(id);
            driver1.setPlaque(driver.getPlaque());
            service.update(driver1);
            return new ResponseEntity<Driver>(driver1, HttpStatus.OK);
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
            Driver driver = service.getById(id);
            service.delete(driver);
            return ResponseEntity.ok().build();
        }return ResponseEntity.notFound().build();

    }

    /**
     * @param id
     * @return
     */
    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<Driver> findById(@PathVariable long id) {
        Driver driver = service.getById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Driver>(driver, HttpStatus.OK);
    }

    @Override
    @GetMapping("getAll")
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> drivers = service.getAll();
        if (drivers == null)
            return ResponseEntity.notFound().build();
        else return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkExistById(long id) {
        return null;
    }

    private void taxiGenerator(){
        for (int i = 0; i < 10; i++) {
            Driver driver = new Driver();
            Adresse adresse = new Adresse();
            adresse.setCommune("Kalamu");
            driver.setPlaque(String.valueOf(new Random().doubles(4).findAny().getAsDouble() * 10000).substring(0, 4).replace('.', '0'));
            driver.setAdresse(adresse);
            driver.setEmail("jos@gmail");
            driver.setNumber(String.valueOf(new Random().nextDouble() * 1000000000).substring(0, 10).replace('.', '0'));
            Position position = new Position();
            position.setLongitude(new Random().nextDouble()*40000);
           position.setLatitude(new Random().nextDouble()*40000);
            driver.setPosition(position);
            service.save(driver);
        }

    }
}
