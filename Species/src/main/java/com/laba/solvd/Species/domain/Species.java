package com.laba.solvd.Species.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;

@JsonRootName("species")
@XmlRootElement(name="species")
@XmlAccessorType(XmlAccessType.FIELD)
public class Species {
    @JsonProperty
    @XmlElement
    private int id;
    @JsonProperty
    @XmlElement
    private String commonName;
    @JsonProperty
    @XmlElement
    private String scientificName;
    @JsonProperty
    @XmlElementWrapper(name="references")
    @XmlElement(name="reference")
    private List<Reference> references;
    @JsonProperty
    @XmlElementWrapper
    @XmlElement(name="image")
    private List<Image> images;
    @JsonProperty
    @XmlElementWrapper
    @XmlElement(name="characteristic")
    private List<Characteristic> characteristics;
    @JsonProperty
    @XmlElement
    private ConservationStatus conservationStatus;
    @JsonProperty
    @XmlElement
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

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", references=" + references +
                ", images=" + images +
                ", characteristics=" + characteristics +
                ", conservationStatus=" + conservationStatus +
                ", family=" + family +
                '}';
    }
}
