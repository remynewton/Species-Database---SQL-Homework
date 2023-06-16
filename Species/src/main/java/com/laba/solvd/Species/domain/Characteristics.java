package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Characteristics {
    private int id;
    private String name;
    private String category;
    private List<Species> species = new ArrayList<>();

    public Characteristics(int id, String name, String category, List<Species> species) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.species = species;
    }

    public Characteristics() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        Characteristics that = (Characteristics) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, species);
    }
}
