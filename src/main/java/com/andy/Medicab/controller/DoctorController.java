package com.andy.Medicab.controller;


import com.andy.Medicab.model.Doctor;
import com.andy.Medicab.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/rest/doctors/")
public class DoctorController implements Crud<Doctor,Long>{
    @Autowired
    private DoctorService service;
    /**
     * @param doctor
     * @return <I>
     */
    @Override
    @PostMapping(path = SAVE_)
    public ResponseEntity<Long> save(@RequestBody Doctor doctor) {

        return new ResponseEntity<>(service.save(doctor), HttpStatus.OK);
    }

    /**
     * @param doctor
     * @param id
     * @return <T>
     */
    @Override
    public ResponseEntity<Doctor> update(Doctor doctor, Long id) {
        return null;
    }

    /**
     * @param doc

     * @return <T>
     */

    @PutMapping(path = UPDATE_+"/")
    public ResponseEntity<Doctor> update(@RequestBody Doctor doc) {
        Doctor d = service.findById(doc.getId());
        if (doc==null){
            return buildErrorMessage("Aucun docteur à mettre à jour");
        }else {
            //doc.setProfil(d.getProfil());
            return new ResponseEntity<>(service.update(doc),HttpStatus.OK);
        }
    }

    /**
     * @param id
     * @return <Void>
     */
    @Override
    @DeleteMapping(path = DELETE_BY_ID+"{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * @param id
     * @return <T>
     */
    @Override
    @GetMapping(path = FIND_BY_ID+"{id}")
    public ResponseEntity<Doctor> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<List < T>>
     */
    @Override
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Doctor>> getAll() {
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }

    /**
     * @param id
     * @return <Boolean>
     */
    @GetMapping(path = CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        return new ResponseEntity<>(service.existById(id),HttpStatus.OK);
    }
}
