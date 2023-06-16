package com.laba.solvd.Species.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class References {
    private int id;
    private String title;
    private String author;
    private Date date;
    private List<Species> species = new ArrayList<>();

    public References(int id, String title, String author, Date date, List<Species> species) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.species = species;
    }

    public References() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        References that = (References) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(date, that.date) && Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, date, species);
    }
}
