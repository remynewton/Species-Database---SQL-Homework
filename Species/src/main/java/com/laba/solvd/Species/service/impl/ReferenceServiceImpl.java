package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.persistence.ReferenceRepository;
import com.laba.solvd.Species.persistence.impl.ReferenceRepositoryImpl;
import com.laba.solvd.Species.service.ReferenceService;

public class ReferenceServiceImpl implements ReferenceService {
    private final ReferenceRepository referencesRepository;

    public ReferenceServiceImpl() {
        this.referencesRepository = new ReferenceRepositoryImpl();
    }

    @Override
    public Reference create(Reference reference) {
        reference.setId(null);
        referencesRepository.create(reference);
        return reference;
    }
}
