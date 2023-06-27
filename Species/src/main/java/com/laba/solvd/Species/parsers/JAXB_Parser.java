package com.laba.solvd.Species.parsers;

import com.laba.solvd.Species.domain.Species;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;

public class JAXB_Parser implements Parser {
    private final Logger logger = Logger.getLogger("GLOBAL");

    @Override
    public Species parse(File file) {
        Species species = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Species.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            species = (Species) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return species;
    }
}
