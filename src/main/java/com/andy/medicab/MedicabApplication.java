package com.andy.medicab;

import com.andy.medicab.model.AdminType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@SpringBootApplication
public class MedicabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicabApplication.class, args);
    }
@RequestMapping("/")
    public String home() {
        
        return "API MEDICAB";
    }
    @GetMapping("allAdminType")
    public ResponseEntity<List<AdminType>> getAllType(){
    List<AdminType> list  = new ArrayList();
    for(AdminType typ : AdminType.values()){
        list.add(typ);
    }
    return new ResponseEntity<List<AdminType>> (list,HttpStatus.OK);
    }
    
}
