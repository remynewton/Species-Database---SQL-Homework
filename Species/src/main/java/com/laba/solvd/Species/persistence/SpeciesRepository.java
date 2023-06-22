package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Image;
import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.domain.Species;

import java.util.List;
import java.util.Optional;

public interface SpeciesRepository {
    void create (Species species);
    List<Species> findAll();
    Optional<Species> findByID(int ID);
    void update (Species species);
    void deleteByID (int id);
    void setReference(Species species, Reference reference);
    void setCharacteristic(Species species, Characteristic characteristic);
    void setImage(Species species, Image image);
}
