package com.laba.solvd.Species.domain;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Image {
    @XmlElement
    private int id;
    @XmlElement
    private String url;
    @XmlElement
    private String format;

    public Image(int id, String url, String format) {
        this.id = id;
        this.url = url;
        this.format = format;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image images = (Image) o;
        return id == images.id && Objects.equals(url, images.url) && Objects.equals(format, images.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, format);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
