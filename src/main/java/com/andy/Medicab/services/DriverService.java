package com.andy.Medicab.services;

import com.andy.Medicab.dao.IDriverDao;
import com.andy.Medicab.model.Driver;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("dirverService")
@Transactional
public class DriverService implements GenericService<Driver,Long> {
    @Autowired
    private IDriverDao dao;
    @Override
    public Long save(Driver driver) {
        Driver driver1 = dao.save(driver);
        return driver1.getId();
    }

    @Override
    public Driver update(Driver driver) {
        if (dao.existsById(driver.getId())){
            Driver driver1 = dao.save(driver);
        return driver1;
        }
        return null;
    }

    @Override
    public Driver findById(Long id) {
        Driver driver = dao.findById(id).get();
        return driver;
    }

    @Override
    public void deleteById(Long id) {
        if(dao.existsById(id))
            dao.deleteById(id);
    }

    @Override
    public List<Driver> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }
  
}
