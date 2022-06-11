package com.andy.Medicab.services;

import com.andy.Medicab.dao.IAccountDao;
import com.andy.Medicab.model.Account;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
/**
 *
 * @author Ir Andy
 */
@Service("accountService")
@Transactional
public class Accountservice implements GenericService<Account,Long> {
    @Autowired
    private IAccountDao dao;
    @Override
    public Long save(Account account) {
        Account account1 = dao.save(account);
        return account1.getId();
    }

    @Override
    public Account update(Account account) {
        Account account1 = dao.save(account);
        return account1;
    }

    @Override
    public Account findById(Long id) {
        Account account = dao.findById(id).get();
        return account;
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }

    public Account connexionByNumber(String number, String password){
        return  dao.findAccountByNumberAndPassword(number, password);
    }
    public  Account findByEmail(String email){
        return dao.findByEmail(email);
    }
}
