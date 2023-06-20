package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.service.CharacteristicsService;
import com.laba.solvd.Species.service.SpeciesService;
import com.laba.solvd.Species.service.impl.CharacteristicsServiceImpl;
import com.laba.solvd.Species.service.impl.SpeciesServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Species polarBear = new Species();
        polarBear.setCommonName("Polar Bear");
        polarBear.setScientificName("Ursus maritimus");
        Characteristics whiteFur = new Characteristics();
        whiteFur.setName("White Fur");
        whiteFur.setCategory("Fur Color");
        Characteristics nocturnal = new Characteristics();
        nocturnal.setName("Nocturnal");
        nocturnal.setCategory("Circadian Rhythms");
        CharacteristicsService cs = new CharacteristicsServiceImpl();
        cs.create(whiteFur);
        cs.create(nocturnal);
        polarBear.setCharacteristics(List.of(nocturnal, whiteFur));
        SpeciesService ss = new SpeciesServiceImpl();
        ss.create(polarBear);
    }
}