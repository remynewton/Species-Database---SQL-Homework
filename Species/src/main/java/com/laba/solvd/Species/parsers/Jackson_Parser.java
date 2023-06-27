package com.laba.solvd.Species.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.laba.solvd.Species.domain.Species;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class Jackson_Parser implements Parser {
    private final Logger logger = Logger.getLogger("GLOBAL");
    @Override
    public Species parse(File file) {
        Species species = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            species = (Species) objectMapper.readValue(file, Species.class);
        } catch (IOException e) {
            logger.error(e);
        }
        return species;
    }
}
