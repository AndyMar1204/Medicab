package com.andy.medicab.controller;

import com.andy.medicab.model.Account;
import com.andy.medicab.services.AccountServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ir Andy
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/account")

public class AccountRestController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = accountService.allAccounts();
        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<Account> getByUsernameAndPassword() {
        return null;
    }

    public ResponseEntity<Account> createNewAccount(@RequestBody Account account) {
        Account existingAccount = accountService.findAccountByUsername(account.getUsername());
        if (existingAccount != null) {
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        } else {
            accountService.saveAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        Account compt = accountService.getById(id);
        if (compt != null) {
            return new ResponseEntity<Account>(compt, HttpStatus.OK);
        } else {
            return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param account
     * @return
     */
    @PostMapping(path = "addAccount")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        Long id = account.getId();
        if (id == null || id == 0) {

            this.accountService.saveAccount(account);
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        } else {
            return updateAccount(account, id);
        }
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable long id) {

        if (accountService.checkIfExist(id)) {
            accountService.deleteAcount(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param ac
     * @param id
     * @return
     */
    @PutMapping(path = "/updateAccount/{id}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account ac, @PathVariable long id) {

        if (accountService.checkIfExist(id)) {
            Account old = accountService.getById(id);
            old.setEmail(ac.getEmail());
            old.setNumber(ac.getNumber());
            old.setPassword(ac.getPassword());
            old.setUsername(ac.getUsername());
            accountService.updateAccount(old);
            return new ResponseEntity<Account>(old, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Account>(ac, HttpStatus.CONFLICT);
        }

    }
}
