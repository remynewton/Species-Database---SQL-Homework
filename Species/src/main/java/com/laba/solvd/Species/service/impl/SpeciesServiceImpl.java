package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import com.laba.solvd.Species.persistence.impl.SpeciesRepositoryImpl;
import com.laba.solvd.Species.service.SpeciesService;

import java.util.List;

public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesServiceImpl(SpeciesRepository speciesRepository) {
        this.speciesRepository = new SpeciesRepositoryImpl();
    }

    @Override
    public void create(Species species) {
        speciesRepository.create(species);
    }

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
}
