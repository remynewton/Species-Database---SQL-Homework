package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.parsers.JAXB_Parser;
import com.laba.solvd.Species.parsers.Jackson_Parser;
import com.laba.solvd.Species.parsers.SAX_Parser;
import com.laba.solvd.Species.service.SpeciesService;
import com.laba.solvd.Species.service.impl.SpeciesServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        String xmlFilePath = "src/main/resources/Species.xml";
        String xsdFilePath = "src/main/resources/SpeciesSchema.xsd";
        String jsonFilePath = "src/main/resources/Species.json";

        // SAX Parser
        SAX_Parser saxParser = new SAX_Parser();
        logger.info(saxParser.validateXMLWithXSD(xmlFilePath, xsdFilePath));
        Species species = saxParser.parse(new File(xmlFilePath));
        logger.info(species.toString());

        //JAXB Parser
        JAXB_Parser jaxbParser = new JAXB_Parser();
        species = jaxbParser.parse(new File(xmlFilePath));
        logger.info(species.toString());

        // Jackson Parser
        Jackson_Parser jacksonParser = new Jackson_Parser();
        species = jacksonParser.parse(new File(jsonFilePath));
        logger.info(species.toString());

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
        SpeciesService ss = new SpeciesServiceImpl();
        ss.create(polarBear);
        System.out.println(polarBear);
        System.out.print(ss.findAll());
    }
}