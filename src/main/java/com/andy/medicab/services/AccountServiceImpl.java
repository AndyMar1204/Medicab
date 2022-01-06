package com.andy.medicab.services;

import com.andy.medicab.dao.IAccountDao;
import com.andy.medicab.model.Account;
import com.andy.medicab.services.interfaces.IAccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ir Andy
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao dao;

    @Override
    public Account saveAccount(Account account) {
        return dao.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return dao.save(account);
    }

    @Override
    public void deleteAcount(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Account> allAccounts() {
        return dao.findAll();
    }

    @Override
    public Account findAccountByUsername(String username) {
        Account account = dao.getAccountByCriteria(username);
        if (account == null) {
            return account;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkIfExist(long id) {
        return dao.existsById(id);
    }

    @Override
    public Account getById(long Id) {
        return dao.findById(Id).get();
    }
    
}
