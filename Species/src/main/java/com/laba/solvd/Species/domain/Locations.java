package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Locations {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private List<Species> species;

    public Locations(int id, String name, double longitude, double latitude, List<Species> species) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
        return id == locations.id && Double.compare(locations.longitude, longitude) == 0 && Double.compare(locations.latitude, latitude) == 0 && Objects.equals(name, locations.name) && Objects.equals(species, locations.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longitude, latitude, species);
    }
}
