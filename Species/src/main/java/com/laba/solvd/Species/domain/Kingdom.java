package com.laba.solvd.Species.domain;


import java.util.List;
import java.util.Objects;

public class Kingdom {
    private int id;
    private String name;
    private List<Class> classes;

    public Kingdom(int id, String name, List<Class> classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public Kingdom() {
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

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kingdom kingdoms = (Kingdom) o;
        return id == kingdoms.id && Objects.equals(name, kingdoms.name) && Objects.equals(classes, kingdoms.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, classes);
    }
}