package com.laba.solvd.Species.domain;

import java.util.Objects;

public class ConservationStatus {
    private int id;
    private String status;

    public ConservationStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public ConservationStatus() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConservationStatus that = (ConservationStatus) o;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
