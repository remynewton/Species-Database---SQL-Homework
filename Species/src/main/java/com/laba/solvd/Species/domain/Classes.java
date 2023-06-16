package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classes {
    private int id;
    private String name;
    private List<Families> families = new ArrayList<>();

    public Classes(int id, String name, List<Families> families) {
        this.id = id;
        this.name = name;
        this.families = families;
    }

    public Classes() {
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

    public List<Families> getFamilies() {
        return families;
    }

    public void setFamilies(List<Families> families) {
        this.families = families;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return id == classes.id && Objects.equals(name, classes.name) && Objects.equals(families, classes.families);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, families);
    }
}
