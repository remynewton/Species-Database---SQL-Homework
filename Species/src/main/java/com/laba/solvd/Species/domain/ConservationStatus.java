package com.laba.solvd.Species.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConservationStatus {
    @JsonProperty
    @XmlElement
    private int id;
    @JsonProperty
    @XmlElement
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

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "ConservationStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
