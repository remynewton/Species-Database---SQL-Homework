package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.*;
import com.laba.solvd.Species.service.CharacteristicService;
import com.laba.solvd.Species.service.SpeciesService;
import com.laba.solvd.Species.service.impl.CharacteristicServiceImpl;
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
        Characteristic whiteFur = new CharacteristicBuilder()
                .setId(2)
                .setName("White Fur")
                .setCategory("Fur Color")
                .getCharacteristic();
        Characteristic nocturnal = new CharacteristicBuilder()
                .setId(3)
                .setName("Nocturnal")
                .setCategory("Circadian Rhythms")
                .getCharacteristic();
        CharacteristicService ch = new CharacteristicServiceImpl();
        System.out.println(ch.findByCategory("Circadian Rhythms"));
        Species polarBear = new SpeciesBuilder()
                .setId(1)
                .setCommonName("Polar Bear")
                .setScientificName("Ursus maritimus")
                .setCharacteristics(List.of(nocturnal, whiteFur))
                .getSpecies();
        SpeciesService ss = new SpeciesServiceImpl();
        System.out.println(polarBear);
        ss.create(polarBear);
        System.out.println(ss.findAll());
    }
}