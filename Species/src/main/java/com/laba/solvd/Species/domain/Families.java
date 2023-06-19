package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Families {
    private int id;
    private String name;
    private List<Species> species;

    public Families(int id, String name, List<Species> species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

    public Families() {
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
        Families families = (Families) o;
        return id == families.id && Objects.equals(name, families.name) && Objects.equals(species, families.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species);
    }
}
