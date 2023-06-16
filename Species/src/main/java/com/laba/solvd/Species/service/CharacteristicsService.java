package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Characteristics;

import java.util.Optional;

public interface CharacteristicsService {
    void create (Characteristics characteristics);
    Optional<Characteristics> findByCategory(String category);
}
