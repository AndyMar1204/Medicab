package com.andy.medicab.services;

import com.andy.medicab.dao.ITaxiDao;
import com.andy.medicab.model.Driver;
import com.andy.medicab.services.interfaces.ITaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("taxiService")
@Transactional
public class TaxiServiceImpl implements ITaxiService {
    @Autowired
    private ITaxiDao dao;
    /**
     * @param driver
     * @return
     */
    @Override
    public Driver save(Driver driver) {
        return dao.save(driver);
    }

    /**
     * @param driver
     * @return
     */
    @Override
    public Driver update(Driver driver) {
        return dao.save(driver);
    }

    /**
     * @param driver
     */
    @Override
    public void delete(Driver driver) {
        dao.delete(driver);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Driver getById(long id) {
        return dao.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<Driver> getAll() {
        return dao.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean checkIfExist(long id) {
        return dao.existsById(id);
    }
}
