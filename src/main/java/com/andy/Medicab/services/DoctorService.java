package com.andy.Medicab.services;

import com.andy.Medicab.dao.IDoctorDao;
import com.andy.Medicab.model.Doctor;
import com.andy.Medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Ir Andy
 */
@Service("doctorService")
@Transactional
public class DoctorService implements GenericService<Doctor,Long> {
    @Autowired
    private IDoctorDao doctorDao;
    /**
     * @param doctor
     * @return
     */
    @Override
    public Long save(Doctor doctor) {
        return doctorDao.save(doctor).getId();
    }

    /**
     * @param doctor
     * @return E
     */
    @Override
    public Doctor update(Doctor doctor) {
        return doctorDao.save(doctor);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Doctor findById(Long id) {
        return doctorDao.getById(id);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
doctorDao.deleteById(id);
    }

    /**
     * @return List<E>
     */
    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    /**
     * @param id
     * @return boolean
     */
    @Override
    public boolean existById(Long id) {
        return false;
    }
}
