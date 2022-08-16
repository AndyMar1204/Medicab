package com.andy.Medicab.services;

import com.andy.Medicab.dao.IUserDao;
import com.andy.Medicab.model.User;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Ir Andy
 */
@Service("userService")
@Transactional
public class UserService implements GenericService<User,Long> {
    @Autowired
    private IUserDao dao;
    @Override
    public Long save(User user) {
        User user1 = dao.save(user);
        return user1.getId();
    }

    @Override
    public User update(User user) {
        if(dao.existsById(user.getId())){
            User user1 = dao.save(user);
            return user1;
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = dao.findById(id).get();
        }catch (Exception ex){

        }

        return user;
    }

    @Override
    public void deleteById(Long id) {
        if (dao.existsById(id))
            dao.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }
}
