package com.laba.solvd.Species.parsers;

import com.laba.solvd.Species.domain.SpeciesList;
import com.laba.solvd.Species.domain.Species;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class JAXB_Parser implements Parser {
    private final Logger logger = Logger.getLogger("GLOBAL");

    @Override
    public List<Species> parse(File file) {
        SpeciesList speciesList = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SpeciesList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            speciesList = (SpeciesList) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            logger.error(e);
        }
        assert speciesList != null;
        return speciesList.getSpeciesList();
    }
}
