package com.laba.solvd.Species.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reference {
    @JsonProperty
    @XmlElement(name="id")
    private int id;
    @JsonProperty
    @XmlElement(name="title")
    private String title;
    @JsonProperty
    @XmlElement
    private String author;
    @JsonProperty
    @XmlElement
    private Date date;

    public Reference(int id, String title, String author, Date date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public Reference() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference that = (Reference) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, date);
    }

    @Override
    public String toString() {
        return "Reference{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}
