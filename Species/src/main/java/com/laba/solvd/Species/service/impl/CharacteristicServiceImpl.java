package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.persistence.CharacteristicRepository;
import com.laba.solvd.Species.persistence.impl.CharacteristicRepositoryImpl;
import com.laba.solvd.Species.persistence.impl.MapperImpl.CharacteristicsMapperImpl;
import com.laba.solvd.Species.service.CharacteristicService;

import java.util.Optional;

public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository characteristicsRepository;

    public CharacteristicServiceImpl() {
        // this.characteristicsRepository = new CharacteristicRepositoryImpl();
        this.characteristicsRepository = new CharacteristicsMapperImpl();
    }

    @Override
    public Characteristic create(Characteristic characteristics) {
        characteristics.setId(null);
        characteristicsRepository.create(characteristics);
        return characteristics;
    }

    @Override
    public Optional<Characteristic> findByCategory(String category) {
        return characteristicsRepository.findByCategory(category);
    }
}
