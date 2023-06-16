package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConservationStatuses {
    private int id;
    private String status;
    private List<Species> species = new ArrayList<>();

    public ConservationStatuses(int id, String status, List<Species> species) {
        this.id = id;
        this.status = status;
        this.species = species;
    }

    public ConservationStatuses() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> speciesList) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConservationStatuses that = (ConservationStatuses) o;
        return id == that.id && Objects.equals(status, that.status) && Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, species);
    }
}
