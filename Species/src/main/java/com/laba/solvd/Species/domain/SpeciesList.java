package com.laba.solvd.Species.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="SpeciesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpeciesList {
    @XmlElement
    private List<Species> speciesList;
    public SpeciesList(List<Species> speciesList) {
        this.speciesList = speciesList;
    }
    public List<Species> getSpeciesList() {
        return speciesList;
    }
}
