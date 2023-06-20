package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.persistence.CharacteristicsRepository;
import com.laba.solvd.Species.persistence.impl.CharacteristicsRepositoryImpl;
import com.laba.solvd.Species.service.CharacteristicsService;

import java.util.Optional;

public class CharacteristicsServiceImpl implements CharacteristicsService {
    private final CharacteristicsRepository characteristicsRepository;

    public CharacteristicsServiceImpl() {
        this.characteristicsRepository = new CharacteristicsRepositoryImpl();
    }

    @Override
    public Characteristics create(Characteristics characteristics, int id) {
        characteristics.setId(null);
        characteristicsRepository.create(characteristics);
        return characteristics;
    }

    @Override
    public void create(Characteristics characteristics) {
        characteristics.setId(null);
        characteristicsRepository.create(characteristics);
    }

    @Override
    public Optional<Characteristics> findByCategory(String category) {
        return characteristicsRepository.findByCategory(category);
    }
}
