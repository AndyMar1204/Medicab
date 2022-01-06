package com.andy.medicab.services;

import com.andy.medicab.dao.ITaxiDao;
import com.andy.medicab.model.Taxi;
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
     * @param taxi
     * @return
     */
    @Override
    public Taxi save(Taxi taxi) {
        return dao.save(taxi);
    }

    /**
     * @param taxi
     * @return
     */
    @Override
    public Taxi update(Taxi taxi) {
        return dao.save(taxi);
    }

    /**
     * @param taxi
     */
    @Override
    public void delete(Taxi taxi) {
dao.delete(taxi);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Taxi getById(long id) {
        return dao.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<Taxi> getAll() {
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
