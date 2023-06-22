package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Image;
import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import com.laba.solvd.Species.persistence.impl.SpeciesRepositoryImpl;
import com.laba.solvd.Species.service.CharacteristicService;
import com.laba.solvd.Species.service.ImageService;
import com.laba.solvd.Species.service.ReferenceService;
import com.laba.solvd.Species.service.SpeciesService;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    private final ReferenceService referenceService;
    private final CharacteristicService characteristicsService;
    private final ImageService imagesService;

    public SpeciesServiceImpl() {
        this.speciesRepository = new SpeciesRepositoryImpl();
        this.referenceService = new ReferenceServiceImpl();
        this.characteristicsService = new CharacteristicServiceImpl();
        this.imagesService = new ImageServiceImpl();
    }

    @Override
    public void create(Species species) {
        species.setId(null);
        speciesRepository.create(species);
        if (species.getReferences() != null) {
            List<Reference> references = species.getReferences().stream()
                    .map(reference -> referenceService.create(reference, species.getId()))
                    .collect(Collectors.toList());
            for (Reference reference : references) {
                speciesRepository.setReference(species, reference);
            }
        }
        if (species.getCharacteristics() != null) {
            List<Characteristic> characteristics = species.getCharacteristics().stream()
                    .map(characteristic -> characteristicsService.create(characteristic, species.getId()))
                    .collect(Collectors.toList());
            for (Characteristic characteristic : characteristics) {
                speciesRepository.setCharacteristic(species, characteristic);
            }
        }
        if (species.getImages() != null) {
            List<Image> images = species.getImages().stream()
                    .map(image -> imagesService.create(image, species.getId()))
                    .collect(Collectors.toList());
            for (Image image : images) {
                speciesRepository.setImage(species, image);
            }
        }
    }

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
}
