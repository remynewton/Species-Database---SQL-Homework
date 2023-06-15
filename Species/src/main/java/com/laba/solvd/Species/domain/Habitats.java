package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Habitats {
    private int id;
    private String name;
    private ArrayList<Locations> locations;

    public Habitats(int id, String name, ArrayList<Locations> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
    }

    public Habitats() {
    }

    public ArrayList<Locations> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Locations> locations) {
        this.locations = locations;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitats habitats = (Habitats) o;
        return id == habitats.id && Objects.equals(name, habitats.name) && Objects.equals(locations, habitats.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locations);
    }
}
