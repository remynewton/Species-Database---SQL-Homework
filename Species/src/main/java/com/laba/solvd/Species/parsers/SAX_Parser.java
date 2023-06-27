package com.laba.solvd.Species.parsers;

import com.laba.solvd.Species.domain.*;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAX_Parser implements Parser {
    private final Logger logger = Logger.getLogger("GLOBAL");

    public String validateXMLWithXSD(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdFilePath));
            Validator validator = schema.newValidator();
            CustomErrorHandler errorHandler = new CustomErrorHandler();
            validator.setErrorHandler(errorHandler);
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);
            if (errorHandler.hasErrors()) {
                for (String error : errorHandler.getErrors()) {
                    logger.error(error);
                }
            } else {
                return "Validation successful. The XML document is valid.";
            }
        } catch (Exception e) {
            logger.error("Validation failed with exception: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Species parse(File file) {
        Species species = null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            Handler handler = new Handler();
            parser.parse(file, handler);
            species = handler.getSpecies();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e);
        }
        return species;
    }

    private static class CustomErrorHandler implements ErrorHandler {
        private boolean validationError;
        private final StringBuilder errors;
        private final Logger logger = Logger.getLogger("GLOBAL");
        public CustomErrorHandler() {
            validationError = false;
            errors = new StringBuilder();
        }

        public boolean hasErrors() {
            return validationError;
        }

        public String[] getErrors() {
            return errors.toString().split(System.lineSeparator());
        }

        @Override
        public void warning(SAXParseException exception) {
            logger.warn("Warning: " + exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) {
            validationError = true;
            logger.error("Error: " + exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) {
            validationError = true;
            logger.fatal("Fatal Error: " + exception.getMessage());
        }
    }
}