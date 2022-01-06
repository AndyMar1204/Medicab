
package com.andy.medicab.services.interfaces;

import com.andy.medicab.model.User;

/**
 *
 * @author Ir Andy
 */


public interface IUserService extends GenericService<User>{

    /**
     *
     * @param telephone
     * @param password
     * @return
     */
    public User connexion(String telephone, String password);
}
