package com.andy.Medicab.dao;

import com.andy.Medicab.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorDao extends JpaRepository<Doctor,Long> {
}
