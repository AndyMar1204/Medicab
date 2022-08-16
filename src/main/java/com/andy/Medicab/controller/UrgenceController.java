package com.andy.Medicab.controller;

import com.andy.Medicab.model.Hopital;
import com.andy.Medicab.model.Urgence;
import com.andy.Medicab.services.HopitalService;
import com.andy.Medicab.services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.spec.ECField;
import java.util.List;

import static com.andy.Medicab.controller.Outils.*;
import com.andy.Medicab.model.Transport;
import com.andy.Medicab.model.User;
import com.andy.Medicab.services.UserService;

/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/urgence/")
public class UrgenceController implements Crud<Urgence,Long>{
    @Autowired
    private UrgenceService service;
    @Autowired
    private UserService userService;
    @Autowired
    private HopitalService hopitalService;
    @PostMapping(path = SAVE_)
    @Override
    public ResponseEntity<Long> save(@RequestBody Urgence urgence) {
        Hopital hopital = Outils.getNearHopital(urgence.getUser().getPosition(), hopitalService.findAll());
        urgence.setHopital(hopital);
        try {
            System.out.println(urgence.toString());
            return new ResponseEntity<Long>(service.save(urgence), HttpStatus.CREATED);
        }catch (Exception ex){
            
            return buildErrorMessage(ex,"Impossible de lancer une urgence, reesayer plus tard");
        }
    }
    @PutMapping(UPDATE_+"{id}")
    @Override
    public ResponseEntity<Urgence> update(@RequestBody Urgence urgence,@PathVariable Long id) {
        try {
            urgence.setId(id);
            return new ResponseEntity<Urgence>(service.update(urgence),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex, "Echec de la mise à jour de l'urgence");
        }
    }
@DeleteMapping(DELETE_BY_ID+"{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return buildSuccessMessage("Urgence supprimé");
        }catch (Exception ex){
            return buildErrorMessage(ex,"Echec de le suppression");
        }
    }
    @GetMapping(FIND_BY_ID+"{id}")
    @Override
    public ResponseEntity<Urgence> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Urgence>(service.findById(id),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Pas d'urgence disponible");
        }
    }
    @GetMapping(FIND_ALL)
    @Override
    public ResponseEntity<List<Urgence>> getAll() {
        try {
            return new ResponseEntity<List<Urgence>>(service.findAll(),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex,"Impossible d'accider aux urgences");
        }
    }
    @GetMapping(CHECK_EXIST_BY_ID+"{id}")
    @Override
    public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
        try {
            return new ResponseEntity<Boolean>(service.existById(id),HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorMessage(ex, "Impossible d'acceder au serveur");
        }
    }
    @PostMapping("save/{id_user}")
    public ResponseEntity<Urgence> addUrgence(@PathVariable long id_user,@RequestBody Urgence urgence){
        User user = this.userService.findById(id_user);
        
        if(user==null)
            return Outils.buildErrorMessage("Utilisateur introuvable, connectez vous à partir d'un compte utilisateur");
        Hopital hopital= Outils.getNearHopital(user.getPosition(), hopitalService.findAll());
        if(hopital == null)
           return Outils.buildErrorMessage("Impossible de localiser un hopital tout près de vous");
        urgence.setHopital(hopital);
        urgence.setTypeTransport(Transport.Taxi);
        urgence.setUser(user);
        try{
            Long id = service.save(urgence);
            return new ResponseEntity(service.findById(id),HttpStatus.OK);
        }catch(Exception ex){
            return Outils.buildErrorMessage("Impossible d'initialiser votre urgence, veuillez reesayer plus tard "+ex.getMessage());
        }
    }
   @GetMapping(path = "findAllByUser/{id_user}")
   public ResponseEntity<List<Urgence>> findAllByUser(@PathVariable Long id_user){
        User user = userService.findById(id_user);
       if (user == null) {
           return buildErrorMessage("Aucun utilisateur trouvé");
       }
        return new ResponseEntity<>(service.findAllUserUrgence(user),HttpStatus.OK);
   }
}
