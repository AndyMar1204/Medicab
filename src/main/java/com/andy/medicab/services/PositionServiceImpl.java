package com.andy.medicab.services;

import com.andy.medicab.dao.IPositionDao;
import com.andy.medicab.model.Position;
import com.andy.medicab.services.interfaces.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("positionService")
@Transactional
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private IPositionDao dao;
    /**
     * @param position
     * @return
     */
    @Override
    public Position save(Position position) {
        return dao.save(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public Position update(Position position) {
        return dao.save(position);
    }

    /**
     * @param position
     */
    @Override
    public void delete(Position position) {
dao.delete(position);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Position getById(long id) {
        return dao.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<Position> getAll() {
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
