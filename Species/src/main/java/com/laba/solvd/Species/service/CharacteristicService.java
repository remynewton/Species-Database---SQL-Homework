package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Characteristic;

import java.util.List;

public interface CharacteristicService {
    Characteristic create (Characteristic characteristics);
    List<Characteristic> findByCategory(String category);
}
