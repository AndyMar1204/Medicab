package com.andy.medicab.dao;

import com.andy.medicab.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxiDao extends JpaRepository<Driver, Long> {
}
