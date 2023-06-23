package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Species;
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
        Species polarBear = new Species();
        polarBear.setCommonName("Polar Bear");
        polarBear.setScientificName("Ursus maritimus");
        Characteristic whiteFur = new Characteristic();
        whiteFur.setName("White Fur");
        whiteFur.setCategory("Fur Color");
        Characteristic nocturnal = new Characteristic();
        nocturnal.setName("Nocturnal");
        nocturnal.setCategory("Circadian Rhythms");
        CharacteristicService cs = new CharacteristicServiceImpl();
        cs.create(whiteFur);
        cs.create(nocturnal);
        polarBear.setCharacteristics(List.of(nocturnal, whiteFur));
        SpeciesService ss = new SpeciesServiceImpl();
        ss.create(polarBear);

        String xmlFilePath = "Species/src/main/resources/Species.xml";
        String xsdFilePath = "Species/src/main/resources/SpeciesSchema.xsd";
        SAXParser saxParser = new SAXParser();
        logger.info(saxParser.validateXMLWithXSD(xmlFilePath, xsdFilePath));
    }
}