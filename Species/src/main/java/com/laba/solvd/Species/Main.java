package com.laba.solvd.Species;

import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.parsers.JAXB_Parser;
import com.laba.solvd.Species.parsers.SAX_Parser;
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

        // SAX Parser
        SAX_Parser saxParser = new SAX_Parser();
        logger.info(saxParser.validateXMLWithXSD(xmlFilePath, xsdFilePath));
        List<Species> speciesList = saxParser.parse(new File(xmlFilePath));
        for (Species species : speciesList) {
            logger.info(species.toString());
        }

        //JAXB Parser
        JAXB_Parser jaxbParser = new JAXB_Parser();
        speciesList = jaxbParser.parse(new File(xmlFilePath));
        for (Species species : speciesList) {
            logger.info(species.toString());
        }
    }
}