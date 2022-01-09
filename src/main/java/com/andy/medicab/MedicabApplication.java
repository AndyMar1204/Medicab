package com.andy.medicab;

import com.andy.medicab.model.AdminType;

import java.util.ArrayList;
import java.util.List;

import com.andy.medicab.model.Position;
import com.andy.medicab.services.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@SpringBootApplication
public class MedicabApplication {
    @Autowired
    private PositionServiceImpl positionService;

    public static void main(String[] args) {
        SpringApplication.run(MedicabApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {

        return "API MEDICAB";
    }

    @GetMapping("allAdminType")
    public ResponseEntity<List<AdminType>> getAllType() {
        List<AdminType> list = new ArrayList();
        for (AdminType typ : AdminType.values()) {
            list.add(typ);
        }
        return new ResponseEntity<List<AdminType>>(list, HttpStatus.OK);
    }

    @PutMapping("/updatePosition")
    public ResponseEntity<Position> updatePosition(@RequestBody Position position) {
        Long id = position.getId();
        if (id != null) {
            if (positionService.checkIfExist(id)) {
                positionService.update(position);
                return new ResponseEntity<Position>(position, HttpStatus.OK);
            } else return ResponseEntity.notFound().build();
        } else return null;
    }


}
