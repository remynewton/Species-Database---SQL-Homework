package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.domain.References;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import com.laba.solvd.Species.persistence.impl.SpeciesRepositoryImpl;
import com.laba.solvd.Species.service.CharacteristicsService;
import com.laba.solvd.Species.service.ReferencesService;
import com.laba.solvd.Species.service.SpeciesService;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    private final ReferencesService referenceService;
    private final CharacteristicsService characteristicsService;

    public SpeciesServiceImpl() {
        this.speciesRepository = new SpeciesRepositoryImpl();
        this.referenceService = new ReferencesServiceImpl();
        this.characteristicsService = new CharacteristicsServiceImpl();
    }

    @Override
    public void create(Species species) {
        species.setId(null);
        speciesRepository.create(species);
        if (species.getReferences() != null) {
            List<References> references = species.getReferences().stream()
                    .map(reference -> referenceService.create(reference, species.getId()))
                    .collect(Collectors.toList());
            species.setReferences(references);
        }
        if (species.getCharacteristics() != null) {
            List<Characteristics> characteristics = species.getCharacteristics().stream()
                    .map(characteristic -> characteristicsService.create(characteristic, species.getId()))
                    .collect(Collectors.toList());
            species.setCharacteristics(characteristics);
        }
    }

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
}
