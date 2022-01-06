
package com.andy.medicab.services;

import com.andy.medicab.dao.IUserDao;
import com.andy.medicab.model.User;
import com.andy.medicab.services.interfaces.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ir Andy
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao dao;
    @Override
    public User connexion(String telephone, String password) {
        return dao.findByNumberAndPassword(telephone, password);
    }

    @Override
    public User save(User t) {
        return dao.save(t);
    }

    @Override
    public User update(User t) {
        return dao.save(t);
    }

    @Override
    public void delete(User t) {
       dao.delete(t);
    }

    @Override
    public User getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return dao.findAll();
    }

    @Override
    public boolean checkIfExist(long id) {
       return dao.existsById(id);
    }
    
}
