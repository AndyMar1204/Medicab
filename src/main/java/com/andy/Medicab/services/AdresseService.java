package com.andy.Medicab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andy.Medicab.dao.IAdresse;
import com.andy.Medicab.model.Adresse;
import com.andy.Medicab.services.interfaces.GenericService;

/**
 * @author Andy
 *
 */
@Service("adresseService")
@Transactional
public class AdresseService implements GenericService<Adresse,Long> {
	@Autowired
	IAdresse dao;

	@Override
	public Long save(Adresse e) {
		// TODO Auto-generated method stub
		return dao.save(e).getId();
	}

	@Override
	public Adresse update(Adresse e) {
		// TODO Auto-generated method stub
		return dao.save(e);
	}

	@Override
	public Adresse findById(Long id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		
	}

	@Override
	public List<Adresse> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean existById(Long id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}

}
