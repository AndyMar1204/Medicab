package com.andy.Medicab.services;

import com.andy.Medicab.dao.IHopitalDao;
import com.andy.Medicab.model.Hopital;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("hopitalService")
@Transactional
public class HopitalService implements GenericService<Hopital,Long> {
    @Autowired
    private IHopitalDao hopitalDao;
    @Override
    public Long save(Hopital hopital) {
        Hopital hopital1 = hopitalDao.save(hopital);
        return hopital1.getId();
    }

    @Override
    public Hopital update(Hopital hopital) {
        if(hopitalDao.existsById(hopital.getId())){
            Hopital hopital1 = hopitalDao.save(hopital);
            return hopital1;
        }
        return null;
    }

    @Override
    public Hopital findById(Long id) {
        Hopital hopital = hopitalDao.findById(id).get();

        return hopital;
    }

    @Override
    public void deleteById(Long id) {
        if (existById(id))
            hopitalDao.deleteById(id);
    }

    @Override
    public List<Hopital> findAll() {
        return hopitalDao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return hopitalDao.existsById(id);
    }
}
