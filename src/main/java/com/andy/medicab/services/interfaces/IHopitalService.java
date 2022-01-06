package com.andy.medicab.services.interfaces;

import com.andy.medicab.model.Hopital;
import com.andy.medicab.model.Position;

import java.util.List;

public interface IHopitalService extends GenericService<Hopital>{
    List<Hopital> getByPosition(Position position);
}
