package com.andy.Medicab.dao;

import com.andy.Medicab.model.Hopital;
import com.andy.Medicab.model.Urgence;
import com.andy.Medicab.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUrgenceDao extends JpaRepository<Urgence, Long> {
    public List<Urgence> findAllByUser(User uSER); 
    public List<Urgence> findAllByHopital(Hopital hOPITAL);
}
