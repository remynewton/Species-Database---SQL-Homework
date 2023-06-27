package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.ConservationStatus;
import com.laba.solvd.Species.domain.Family;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.service.SpeciesService;
import com.laba.solvd.Species.service.impl.SpeciesServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        String xmlFilePath = "src/main/resources/Species.xml";
        String xsdFilePath = "src/main/resources/SpeciesSchema.xsd";
        String jsonFilePath = "src/main/resources/Species.json";

        // MyBatis
        Species polarBear = new Species();
        polarBear.setCommonName("Polar Bear");
        polarBear.setScientificName("Ursus maritimus");
        Characteristic whiteFur = new Characteristic();
        whiteFur.setName("White Fur");
        whiteFur.setCategory("Fur Color");
        Characteristic nocturnal = new Characteristic();
        nocturnal.setName("Nocturnal");
        nocturnal.setCategory("Circadian Rhythms");
        polarBear.setCharacteristics(List.of(nocturnal, whiteFur));
        ConservationStatus endangered = new ConservationStatus();
        endangered.setStatus("Endangered");
        polarBear.setConservationStatus(endangered);
        Family ursidae = new Family();
        ursidae.setName("Ursidae");
        polarBear.setFamily(ursidae);
        SpeciesService ss = new SpeciesServiceImpl();
        ss.create(polarBear);
        System.out.println(polarBear);
        System.out.print(ss.findAll());
    }
}