package com.andy.Medicab.dao;

import com.andy.Medicab.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionDao extends JpaRepository<Position,Long> {
}
