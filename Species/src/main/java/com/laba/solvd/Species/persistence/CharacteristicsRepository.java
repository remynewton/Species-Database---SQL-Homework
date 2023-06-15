package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Characteristics;

import java.sql.SQLException;
import java.util.Optional;

public interface CharacteristicsRepository {
    void create (Characteristics characteristic) throws SQLException, ClassNotFoundException;
    void update (Characteristics characteristic) throws SQLException, ClassNotFoundException;
    Optional<Characteristics> findByCategory(String category) throws SQLException, ClassNotFoundException;
    Optional<Characteristics> findByID(int id) throws SQLException, ClassNotFoundException;
    void deleteByID(int id) throws SQLException, ClassNotFoundException;
}