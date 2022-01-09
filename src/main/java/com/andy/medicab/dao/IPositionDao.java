package com.andy.medicab.dao;

import com.andy.medicab.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionDao extends JpaRepository<Position,Long> {
}
