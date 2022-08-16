package com.andy.Medicab;

import com.andy.Medicab.model.*;
import com.andy.Medicab.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.andy.Medicab.controller.Outils.*;

@CrossOrigin({BASE_URL})
@RestController
@SpringBootApplication
public class MedicabApiApplication {
    @Autowired
    private Accountservice accountservice;
    @Autowired
    private UserService userService;
    @Autowired
    private HopitalService hopitalService;
    @Autowired
    private DriverService driverService;

    @Autowired
    private AdresseService adresseService;
    public static void main(String[] args) {
        
        SpringApplication.run(MedicabApiApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {

        return "API MEDICAB v2.0.0";
    }

    @PostMapping(path = "inscription/{typeAccount}")
    public ResponseEntity inscription(@RequestBody Account account, @PathVariable String typeAccount) {
        try {
            if (typeAccount == null)
                throw new Exception("Type de compte pas pris en charge");
            if (account == null)
                throw new Exception("Aucun compte à sauvegarder");
            account.setPassword(encryptPassword(account.getPassword()));
            switch (typeAccount.trim().toLowerCase()) {
                case ATT_USER:
                    userService.save((User) account);
                    break;
                case ATT_HOPITAL:
                    hopitalService.save((Hopital) account);
                    break;
                case ATT_DRIVER:
                    driverService.save((Driver) account);
                    break;
                default:
                    accountservice.save(account);
                    break;
            }
            return buildSuccessMessage("Enregistrement reussie");
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorMessage(ex, "Echec d'enregistrement " + ex.getMessage());
        }

    }

    @GetMapping(path = "connexion/{number}/{password}")
    public ResponseEntity<Account> connectionByNumberPassword(@PathVariable String number, @PathVariable String password) {
        try {
            Account account = accountservice.connexionByNumber(number, encryptPassword(password));
            if (account == null) {
                return buildErrorMessage( "Impossible de se connecter!!!!Reverifiez vos identifiants");
            } else
                return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorMessage(ex, "Impossible d'acceder à la base de donnees");
        }
    }
    @GetMapping(path = "connexionSecure/{number}/{password}")
    public ResponseEntity<Account> connectionByNumberHashedPassPassword(@PathVariable String number, @PathVariable String password) {
        try {
            Account account = accountservice.connexionByNumber(number, password);
            if (account == null) {
                return buildErrorMessage( "Impossible de se connecter!!!!Reverifiez vos identifiants");
            } else
                return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorMessage(ex, "Impossible d'acceder à la base de donnees");
        }
    }

    @GetMapping(path = "getAllUrgences")
    public ResponseEntity<List<Urgences>> listUrgences() {
        List<Urgences> urgences = Arrays.asList(Urgences.values());
        List<Urgences> urgencesList = urgences.stream().sorted().collect(Collectors.toList());
       // Collections.sort(urgences);
        return new ResponseEntity<List<Urgences>>(urgencesList, HttpStatus.OK);
    }

    @GetMapping(path = "getAllTypesTrans")
    public ResponseEntity<List<Transport>> getAllTypeTrans() {
        List<Transport> list = new ArrayList<>();
        for (Transport t : Transport.values())
            list.add(t);

        Collections.sort(list);
        return new ResponseEntity<List<Transport>>(list, HttpStatus.OK);
    }
    @PutMapping(path="updateAdresse")
    public ResponseEntity<Adresse> updateAdresse(@RequestBody Adresse adresse){
        if (adresseService.existById(adresse.getId())){
            return  new ResponseEntity<>(adresseService.update(adresse),HttpStatus.OK);
        }else return buildErrorMessage("Echec de mise à jour");
    }
}
