package com.laba.solvd.Species.domain;

import java.util.List;
import java.util.Objects;

public class Habitat {
    private int id;
    private String name;
    private List<Location> locations;

    public Habitat(int id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
    }

    public Habitat() {
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitats = (Habitat) o;
        return id == habitats.id && Objects.equals(name, habitats.name) && Objects.equals(locations, habitats.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locations);
    }
}
