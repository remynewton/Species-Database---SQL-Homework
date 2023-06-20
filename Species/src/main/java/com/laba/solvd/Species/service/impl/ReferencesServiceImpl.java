package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.References;
import com.laba.solvd.Species.persistence.ReferencesRepository;
import com.laba.solvd.Species.persistence.impl.ReferencesRepositoryImpl;
import com.laba.solvd.Species.service.ReferencesService;

public class ReferencesServiceImpl implements ReferencesService {
    private final ReferencesRepository referencesRepository;

    public ReferencesServiceImpl() {
        this.referencesRepository = new ReferencesRepositoryImpl();
    }

    @Override
    public References create(References reference, int id) {
        reference.setId(null);
        referencesRepository.create(reference);
        return reference;
    }
}
