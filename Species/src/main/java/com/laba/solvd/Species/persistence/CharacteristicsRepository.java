package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Characteristics;

import java.sql.SQLException;
import java.util.Optional;

public interface CharacteristicsRepository {
    void create (Characteristics characteristic);
    void update (Characteristics characteristic);
    Optional<Characteristics> findByCategory(String category);
    Optional<Characteristics> findByID(int id);
    void deleteByID(int id);
}