package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Locations {
    private int id;
    private String name;
    private double[] coordinates = new double[2];
    private List<Species> species = new ArrayList<>();

    public Locations(int id, String name, double[] coordinates, List<Species> species) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.species = species;
    }

    public Locations() {
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

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
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
        Locations locations = (Locations) o;
        return id == locations.id && Objects.equals(name, locations.name) && Arrays.equals(coordinates, locations.coordinates) && Objects.equals(species, locations.species);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, species);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
