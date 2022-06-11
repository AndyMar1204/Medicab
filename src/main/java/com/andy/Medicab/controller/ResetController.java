package com.andy.Medicab.controller;

import com.andy.Medicab.model.Account;
import com.andy.Medicab.model.Reset;
import com.andy.Medicab.services.Accountservice;
import com.andy.Medicab.services.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.andy.Medicab.controller.Outils.BASE_URL;

@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("rest/reset/")
public class ResetController  {
    @Autowired
    private Accountservice accountService;
    @Autowired
    private ResetService serv;

    @GetMapping(path = "etap_1/{email}")
    public ResponseEntity<Reset> etap_1(@PathVariable String email) {
        Account account = this.accountService.findByEmail(email);
        if (account == null)
            return ResponseEntity.notFound().build();
        else {
            Reset restaure = new Reset();
            restaure.setCode(Outils.codeGenerator());
            restaure.setAccount(account);
            restaure.setDateCreated(LocalDateTime.now());
            restaure.setId(this.serv.save(restaure));
            return new ResponseEntity<Reset>(restaure, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "etap_2/{id}/{code}")
    public ResponseEntity<Boolean> etap_2(@PathVariable Long id, @PathVariable String code) {
        Reset restaure = serv.findById(id);
        if (restaure != null) {
            if (restaure.getCode().matches(code))
                return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
            else return new ResponseEntity<Boolean>(false, HttpStatus.ACCEPTED);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "etap_3/{id}/{password}/{password_confirm}")
    public ResponseEntity<Boolean> etap_3(@PathVariable Long id, @PathVariable String password, @PathVariable String password_confirm) {
        Account account = this.accountService.findById(id);
        if (account != null) {
            if (password.matches(password_confirm)) {
                account.setPassword(Outils.encryptPassword(password));
                accountService.update(account);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}

