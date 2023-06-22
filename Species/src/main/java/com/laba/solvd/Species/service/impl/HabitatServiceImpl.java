package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Habitat;
import com.laba.solvd.Species.persistence.HabitatRepository;
import com.laba.solvd.Species.persistence.impl.HabitatRepositoryImpl;
import com.laba.solvd.Species.service.HabitatService;

public class HabitatServiceImpl implements HabitatService {
    private final HabitatRepository habitatsRepository;

    public HabitatServiceImpl() {
        this.habitatsRepository = new HabitatRepositoryImpl();
    }

    @Override
    public void create(Habitat habitats) {
        habitats.setId(null);
        habitatsRepository.create(habitats);
    }
}
