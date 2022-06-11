
package com.andy.Medicab.dao;

import com.andy.Medicab.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ir Andy
 */

@Repository
public interface IAccountDao extends JpaRepository<Account, Long> {

    /**
     *
     * @param nUMBER
     * @param pASSWORD
     * @return
     */
    public List<Account> findByNumberAndPasswordIgnoreCase(String nUMBER, String pASSWORD) throws  DaoException;

    /**
     *
     * @param username
     * @return
     */
    @Query("SELECT ac FROM Account ac WHERE ac.username = ?1")
    public Account getAccountByCriteria(String username);

    public  Account findAccountByNumberAndPassword(String nUMBER, String pASSWORD);
    public Account findByEmail(String eMAIL);
}
