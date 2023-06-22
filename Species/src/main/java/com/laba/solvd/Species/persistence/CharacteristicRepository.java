package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Characteristic;

import java.util.Optional;

public interface CharacteristicRepository {
    void create (Characteristic characteristic);
    void update (Characteristic characteristic);
    Optional<Characteristic> findByCategory(String category);
    Optional<Characteristic> findByID(int id);
    void deleteByID(int id);
}