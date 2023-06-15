package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Species;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SpeciesRepository {
    void create (Species species) throws SQLException, ClassNotFoundException;
    List<Species> findAll() throws SQLException, ClassNotFoundException;
    Optional<Species> findByID(int ID) throws SQLException, ClassNotFoundException;
    void update (Species species) throws SQLException, ClassNotFoundException;
    void deleteByID (int id) throws SQLException, ClassNotFoundException;
}
