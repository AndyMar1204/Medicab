package com.andy.Medicab.services;

import com.andy.Medicab.dao.IPositionDao;
import com.andy.Medicab.model.Position;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service("positionService")
@Transactional
public class PositionService implements GenericService<Position,Long> {
    @Autowired
    private IPositionDao dao;
    @Override
    public Long save(Position position) {
        Position position1 = dao.save(position);
        return position1.getId();
    }

    @Override
    public Position update(Position position) {
        if (dao.existsById(position.getId())){
            Position position1 = dao.save(position);
            return position1;
        }
        return null;
    }

    @Override
    public Position findById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Position> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }
}
