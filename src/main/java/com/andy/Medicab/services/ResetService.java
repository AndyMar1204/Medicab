package com.andy.Medicab.services;

import com.andy.Medicab.dao.IReset;
import com.andy.Medicab.model.Reset;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("resetService")
@Transactional
public class ResetService implements GenericService<Reset,Long> {
    @Autowired
    private IReset dao;
    @Override
    public Long save(Reset reset) {
        return dao.save(reset).getId();
    }

    @Override
    public Reset update(Reset reset) {
        if (dao.existsById(reset.getId()))
            return  dao.save(reset);
        return null;
    }

    @Override
    public Reset findById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        if (dao.existsById(id))
            dao.deleteById(id);
    }

    @Override
    public List<Reset> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }
}
