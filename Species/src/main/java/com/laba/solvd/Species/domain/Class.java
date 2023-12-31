package com.laba.solvd.Species.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Class {
    @JsonProperty
    @XmlElement
    private Integer id;
    @JsonProperty
    @XmlElement
    private String name;
    @JsonProperty
    @XmlElement
    private List<Family> families;

    public Class(Integer id, String name, List<Family> families) {
        this.id = id;
        this.name = name;
        this.families = families;
    }

    public Class() {
    }

    public Integer getId() {
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

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class classes = (Class) o;
        return id == classes.id && Objects.equals(name, classes.name) && Objects.equals(families, classes.families);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, families);
    }
}
