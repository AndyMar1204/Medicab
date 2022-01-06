
package com.andy.medicab.services.interfaces;

import com.andy.medicab.model.Account;
import java.util.List;

/**
 *
 * @author Ir Andy
 */


public interface IAccountService {

    /**
     *
     * @param account
     * @return
     */
    public Account saveAccount(Account account);

    /**
     *
     * @param account
     * @return
     */
    public Account updateAccount(Account account);

    /**
     *
     * @param id
     */
    public void deleteAcount(Long id);
    
    /**
     *
     * @return
     */
    public List<Account> allAccounts();
    
    /**
     *
     * @param username
     * @return
     */
    public Account findAccountByUsername(String username);
    
    /**
     *
     * @param id
     * @return
     */
    public boolean checkIfExist(long id);
    
    /**
     *
     * @param Id
     * @return
     */
    public Account getById(long Id);
    
}
