package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.ConservationStatus;
import com.laba.solvd.Species.persistence.ConservationStatusRepository;
import com.laba.solvd.Species.persistence.impl.MapperImpl.ConservationStatusMapperImpl;
import com.laba.solvd.Species.service.ConservationService;

public class ConservationServiceImpl implements ConservationService {
    private final ConservationStatusRepository conservationStatusRepository;

    public ConservationServiceImpl() {
        this.conservationStatusRepository = new ConservationStatusMapperImpl();
    }


    @Override
    public ConservationStatus create(ConservationStatus conservationStatus) {
        conservationStatus.setId(null);
        conservationStatusRepository.create(conservationStatus);
        return conservationStatus;
    }
}
