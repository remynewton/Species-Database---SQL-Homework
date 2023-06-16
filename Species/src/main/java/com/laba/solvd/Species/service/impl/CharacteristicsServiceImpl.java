package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.persistence.CharacteristicsRepository;
import com.laba.solvd.Species.persistence.impl.CharacteristicsRepositoryImpl;
import com.laba.solvd.Species.service.CharacteristicsService;

import java.util.Optional;

public class CharacteristicsServiceImpl implements CharacteristicsService {
    private final CharacteristicsRepository characteristicsRepository;

    public CharacteristicsServiceImpl(CharacteristicsRepository characteristicsRepository) {
        this.characteristicsRepository = new CharacteristicsRepositoryImpl();
    }

    @Override
    public void create(Characteristics characteristics) {
        characteristicsRepository.create(characteristics);
    }

    @Override
    public Optional<Characteristics> findByCategory(String category) {
        return characteristicsRepository.findByCategory(category);
    }
}
