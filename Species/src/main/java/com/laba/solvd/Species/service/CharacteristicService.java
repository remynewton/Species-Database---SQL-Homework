package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Characteristic;

import java.util.Optional;

public interface CharacteristicService {
    Characteristic create (Characteristic characteristics, int id);
    void create (Characteristic characteristics);
    Optional<Characteristic> findByCategory(String category);
}
