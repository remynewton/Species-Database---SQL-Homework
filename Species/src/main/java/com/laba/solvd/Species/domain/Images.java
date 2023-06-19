package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Images {
    private int id;
    private String url;
    private String format;
    private List<Species> species;

    public Images(int id, String url, String format, List<Species> species) {
        this.id = id;
        this.url = url;
        this.format = format;
        this.species = species;
    }

    public Images() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images images = (Images) o;
        return id == images.id && Objects.equals(url, images.url) && Objects.equals(format, images.format) && Objects.equals(species, images.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, format, species);
    }
}
