package com.andy.medicab.dao;

import com.andy.medicab.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaxiDao extends JpaRepository<Taxi,Long> {
}
