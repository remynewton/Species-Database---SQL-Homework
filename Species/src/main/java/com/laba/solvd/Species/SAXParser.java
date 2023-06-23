package com.laba.solvd.Species;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SAXParser {
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
        public void warning(SAXParseException exception) throws SAXException {
            logger.warn("Warning: " + exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            validationError = true;
            logger.error("Error: " + exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            validationError = true;
            logger.fatal("Fatal Error: " + exception.getMessage());
        }
    }
}