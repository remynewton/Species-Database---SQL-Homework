package com.laba.solvd.Species.domain;

import java.util.List;
import java.util.Objects;

public class Species {
    private int id;
    private String commonName;
    private String scientificName;
    private List<References> references;
    private List<Images> images;
    private List<Characteristics> characteristics;
    ConservationStatuses conservationStatus;
    Families family;

    public Species(int id, String commonName, String scientificName, List<References> references, List<Images> images, List<Characteristics> characteristics, ConservationStatuses conservationStatus, Families family) {
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

    public List<References> getReferences() {
        return references;
    }

    public void setReferences(List<References> references) {
        this.references = references;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public ConservationStatuses getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(ConservationStatuses conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public Families getFamily() {
        return family;
    }

    public void setFamily(Families family) {
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
