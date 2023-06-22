package com.laba.solvd.Species.domain;

import java.util.List;
import java.util.Objects;

public class Species {
    private int id;
    private String commonName;
    private String scientificName;
    private List<Reference> references;
    private List<Image> images;
    private List<Characteristic> characteristics;
    private ConservationStatus conservationStatus;
    private Family family;

    public Species(int id, String commonName, String scientificName, List<Reference> references, List<Image> images, List<Characteristic> characteristics, ConservationStatus conservationStatus, Family family) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.references = references;
        this.images = images;
        this.characteristics = characteristics;
        this.conservationStatus = conservationStatus;
        this.family = family;
    }

    public Species() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public ConservationStatus getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return id == species.id && Objects.equals(commonName, species.commonName) && Objects.equals(scientificName, species.scientificName) && Objects.equals(references, species.references) && Objects.equals(images, species.images) && Objects.equals(characteristics, species.characteristics) && Objects.equals(conservationStatus, species.conservationStatus) && Objects.equals(family, species.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commonName, scientificName, references, images, characteristics, conservationStatus, family);
    }
}
