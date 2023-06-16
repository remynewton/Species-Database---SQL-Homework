package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.References;
import com.laba.solvd.Species.persistence.ReferencesRepository;
import com.laba.solvd.Species.persistence.impl.ReferencesRepositoryImpl;
import com.laba.solvd.Species.service.ReferencesService;

public class ReferencesServiceImpl implements ReferencesService {
    private final ReferencesRepository referencesRepository;

    public ReferencesServiceImpl(ReferencesRepository referencesRepository) {
        this.referencesRepository = new ReferencesRepositoryImpl();
    }

    @Override
    public void create(References reference) {
        referencesRepository.create(reference);
    }
}
