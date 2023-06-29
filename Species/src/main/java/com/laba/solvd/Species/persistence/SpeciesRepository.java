package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
public interface SpeciesRepository {
    void create (Species species);
    List<Species> findAll();
    Species findByID(int ID);
    void update (Species species);
    void deleteByID (int id);
    void setReference(@Param("species") Species species, @Param("reference") Reference reference);
    void setCharacteristic(@Param("species") Species species, @Param("characteristic") Characteristic characteristic);
    void setImage(@Param("species") Species species, @Param("image") Image image);
    void setConservationStatus(@Param("species") Species species, @Param("conservationStatus") ConservationStatus conservationStatus);
}
