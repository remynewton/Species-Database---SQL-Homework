package com.laba.solvd.Species.domain;

import com.laba.solvd.Species.domain.Species;
import java.util.List;

public class SpeciesBuilder {
    private Integer id;
    private String commonName;
    private String scientificName;
    private List<Reference> references;
    private List<Image> images;
    private List<Characteristic> characteristics;
    private ConservationStatus conservationStatus;
    private Family family;

    public SpeciesBuilder() {
    }

    public SpeciesBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public SpeciesBuilder setCommonName(String commonName) {
        this.commonName = commonName;
        return this;
    }

    public SpeciesBuilder setScientificName(String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    public SpeciesBuilder setReferences(List<Reference> references) {
        this.references = references;
        return this;
    }

    public SpeciesBuilder setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public SpeciesBuilder setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    public SpeciesBuilder setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
        return this;
    }

    public SpeciesBuilder setFamily(Family family) {
        this.family = family;
        return this;
    }
    public Species getSpecies() {
        return new Species(id, commonName, scientificName, references, images, characteristics, conservationStatus, family);
    }
}
