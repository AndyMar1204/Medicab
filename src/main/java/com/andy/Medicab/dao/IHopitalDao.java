package com.andy.Medicab.dao;

import com.andy.Medicab.model.Hopital;
import com.andy.Medicab.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHopitalDao extends JpaRepository<Hopital,Long> {

    //@Query("SELECT hop from Hopital hop where hop.position = :1")
    public List<Hopital> getAllByPosition(Position position);
   
}
