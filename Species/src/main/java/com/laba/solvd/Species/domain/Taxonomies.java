package com.laba.solvd.Species.domain;

import java.util.Arrays;

public class Taxonomies {
    private String[] kingdom = new String[2];
    private String[] itsClass = new String[3];
    private String[] family = new String[3];

    public Taxonomies(String[] kingdom, String[] itsClass, String[] family) {
        this.kingdom = kingdom;
        this.itsClass = itsClass;
        this.family = family;
    }

    public Taxonomies() {
    }

    public String[] getKingdom() {
        return kingdom;
    }

    public void setKingdom(String[] kingdom) {
        this.kingdom = kingdom;
    }

    public String[] getItsClass() {
        return itsClass;
    }

    public void setItsClass(String[] itsClass) {
        this.itsClass = itsClass;
    }

    public String[] getFamily() {
        return family;
    }

    public void setFamily(String[] family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxonomies that = (Taxonomies) o;
        return Arrays.equals(kingdom, that.kingdom) && Arrays.equals(itsClass, that.itsClass) && Arrays.equals(family, that.family);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(kingdom);
        result = 31 * result + Arrays.hashCode(itsClass);
        result = 31 * result + Arrays.hashCode(family);
        return result;
    }
}
