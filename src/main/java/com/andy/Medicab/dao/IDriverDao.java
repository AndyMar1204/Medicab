package com.andy.Medicab.dao;

import com.andy.Medicab.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDriverDao extends JpaRepository<Driver, Long> {
}
