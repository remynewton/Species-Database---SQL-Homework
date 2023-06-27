package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.*;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import com.laba.solvd.Species.persistence.impl.MapperImpl.SpeciesMapperImpl;
import com.laba.solvd.Species.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    private final ReferenceService referenceService;
    private final CharacteristicService characteristicsService;
    private final ImageService imagesService;
    private final ConservationService conservationService;

    public SpeciesServiceImpl() {
        this.conservationService = new ConservationServiceImpl();
        // this.speciesRepository = new SpeciesRepositoryImpl();
        this.speciesRepository = new SpeciesMapperImpl();
        this.referenceService = new ReferenceServiceImpl();
        this.characteristicsService = new CharacteristicServiceImpl();
        this.imagesService = new ImageServiceImpl();
    }

    @Override
    public void create(Species species) {
        species.setId(0);
        speciesRepository.create(species);
        if (species.getReferences() != null) {
            List<Reference> references = species.getReferences().stream()
                    .map(referenceService::create)
                    .collect(Collectors.toList());
            for (Reference reference : references) {
                speciesRepository.setReference(species, reference);
            }
        }
        if (species.getCharacteristics() != null) {
            List<Characteristic> characteristics = species.getCharacteristics().stream()
                    .map(characteristicsService::create)
                    .collect(Collectors.toList());
            for (Characteristic characteristic : characteristics) {
                speciesRepository.setCharacteristic(species, characteristic);
            }
        }
        if (species.getImages() != null) {
            List<Image> images = species.getImages().stream()
                    .map(imagesService::create)
                    .collect(Collectors.toList());
            for (Image image : images) {
                speciesRepository.setImage(species, image);
            }
        }
        if (species.getConservationStatus() != null) {
            ConservationStatus conservationStatus = conservationService.create(species.getConservationStatus());
            speciesRepository.setConservationStatus(species, conservationStatus);
        }
    }

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
}
