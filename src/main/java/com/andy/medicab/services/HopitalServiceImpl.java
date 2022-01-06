package com.andy.medicab.services;

import com.andy.medicab.dao.IHopitalDao;
import com.andy.medicab.model.Hopital;
import com.andy.medicab.model.Position;
import com.andy.medicab.services.interfaces.IHopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hopitalService")
@Transactional
public class HopitalServiceImpl implements IHopitalService {
    @Autowired
    IHopitalDao dao;
    @Override
    public List<Hopital> getByPosition(Position position) {
        return dao.getAllByPosition(position);
    }

    /**
     * @param hopital
     * @return
     */
    @Override
    public Hopital save(Hopital hopital) {
        return dao.save(hopital);
    }

    /**
     * @param hopital
     * @return
     */
    @Override
    public Hopital update(Hopital hopital) {
        return dao.save(hopital);
    }

    /**
     * @param hopital
     */
    @Override
    public void delete(Hopital hopital) {
dao.delete(hopital);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Hopital getById(long id) {
        return dao.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<Hopital> getAll() {
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
