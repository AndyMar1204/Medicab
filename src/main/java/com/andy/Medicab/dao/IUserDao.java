
package com.andy.Medicab.dao;

import com.andy.Medicab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ir Andy
 */

@Repository
public interface IUserDao extends JpaRepository<User,Long>{
    public User findByNumberAndPassword(String nUMBER, String pASSWORD);
@Query("SELECT  us from User us where us.number= :number and  us.password =:password")
    public User signin(String number, String password);

public User findByEmail(String eMAIL);
}
