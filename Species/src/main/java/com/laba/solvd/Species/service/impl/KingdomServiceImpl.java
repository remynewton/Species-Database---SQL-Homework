package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Kingdom;
import com.laba.solvd.Species.persistence.KingdomRepository;
import com.laba.solvd.Species.persistence.impl.KingdomRepositoryImpl;
import com.laba.solvd.Species.persistence.impl.MapperImpl.KingdomMapperImpl;
import com.laba.solvd.Species.service.KingdomService;

public class KingdomServiceImpl implements KingdomService {
    private final KingdomRepository kingdomsRepository;

    public KingdomServiceImpl() {
        // this.kingdomsRepository = new KingdomRepositoryImpl();
        this.kingdomsRepository = new KingdomMapperImpl();
    }

    @Override
    public void create(Kingdom kingdom) {
        kingdomsRepository.create(kingdom);
    }
}
