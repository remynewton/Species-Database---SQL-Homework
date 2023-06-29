package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.ConservationStatus;
import com.laba.solvd.Species.domain.Family;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.service.CharacteristicService;
import com.laba.solvd.Species.service.ConservationService;
import com.laba.solvd.Species.service.SpeciesService;
import com.laba.solvd.Species.service.impl.CharacteristicServiceImpl;
import com.laba.solvd.Species.service.impl.ConservationServiceImpl;
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
        polarBear.setId(40);
        polarBear.setCommonName("Polar Bear");
        polarBear.setScientificName("Ursus maritimus");
        Characteristic whiteFur = new Characteristic();
        whiteFur.setId(43);
        whiteFur.setName("White Fur");
        whiteFur.setCategory("Fur Color");
        Characteristic nocturnal = new Characteristic();
        nocturnal.setId(42);
        nocturnal.setName("Nocturnal");
        nocturnal.setCategory("Circadian Rhythms");
        CharacteristicService ch = new CharacteristicServiceImpl();
        System.out.println(ch.findByCategory("Circadian Rhythms"));
        polarBear.setCharacteristics(List.of(nocturnal, whiteFur));
        SpeciesService ss = new SpeciesServiceImpl();
        System.out.println(polarBear);
        ss.create(polarBear);
        System.out.println(ss.findAll());
    }
}