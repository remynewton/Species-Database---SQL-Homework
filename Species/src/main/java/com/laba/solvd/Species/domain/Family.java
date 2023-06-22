package com.laba.solvd.Species.domain;

import java.util.Objects;

public class Family {
    private int id;
    private String name;

    public Family(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Family() {
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
        Family families = (Family) o;
        return id == families.id && Objects.equals(name, families.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
