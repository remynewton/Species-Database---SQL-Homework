package com.laba.solvd.Species.parsers;

import com.laba.solvd.Species.domain.*;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Handler extends DefaultHandler {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private StringBuilder data;
    private List<Species> speciesList;
    private Species species;
    private List<Reference> referencesList;
    private Reference reference;
    private List<Image> imagesList;
    private Image image;
    private List<Characteristic> characteristicsList;
    private Characteristic characteristic;
    private ConservationStatus conservationStatus;
    private Family family;

    public List<Species> getSpeciesList() {
        return speciesList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();
        switch (qName) {
            case "SpeciesList":
                speciesList = new ArrayList<>();
                break;
            case "species":
                species = new Species();
                break;
            case "references":
                referencesList = new ArrayList<>();
                break;
            case "reference":
                reference = new Reference();
                break;
            case "images":
                imagesList = new ArrayList<>();
                break;
            case "image":
                image = new Image();
                break;
            case "characteristics":
                characteristicsList = new ArrayList<>();
                break;
            case "characteristic":
                characteristic = new Characteristic();
                break;
            case "conservationStatus":
                conservationStatus = new ConservationStatus();
                break;
            case "family":
                family = new Family();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "id":
                int id = Integer.parseInt(data.toString().trim());
                if (species != null) {
                    species.setId(id);
                } else if (reference != null) {
                    reference.setId(id);
                } else if (image != null) {
                    image.setId(id);
                } else if (characteristic != null) {
                    characteristic.setId(id);
                } else if (conservationStatus != null) {
                    conservationStatus.setId(id);
                } else if (family != null) {
                    family.setId(id);
                }
                break;
            case "commonName":
                String commonName = data.toString().trim();
                if (species != null) {
                    species.setCommonName(commonName);
                }
                break;
            case "scientificName":
                String scientificName = data.toString().trim();
                if (species != null) {
                    species.setScientificName(scientificName);
                }
                break;
            case "name":
                String name = data.toString().trim();
                if (characteristic != null) {
                    characteristic.setName(name);
                } else if (family != null) {
                    family.setName(name);
                }
                break;
            case "title":
                String title = data.toString().trim();
                if (reference != null) {
                    reference.setTitle(title);
                }
                break;
            case "author":
                String author = data.toString().trim();
                if (reference != null) {
                    reference.setAuthor(author);
                }
                break;
            case "date":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = formatter.parse(data.toString().trim());
                } catch (ParseException e) {
                    logger.error(e);
                }
                if (reference != null) {
                    reference.setDate(date);
                }
                break;
            case "url":
                String url = data.toString().trim();
                if (image != null) {
                    image.setUrl(url);
                }
                break;
            case "format":
                String format = data.toString().trim();
                if (image != null) {
                    image.setFormat(format);
                }
                break;
            case "category":
                String category = data.toString().trim();
                if (characteristic != null) {
                    characteristic.setCategory(category);
                }
                break;
            case "status":
                String status = data.toString().trim();
                if (conservationStatus != null) {
                    conservationStatus.setStatus(status);
                }
                break;
            case "species":
                speciesList.add(species);
                species = null;
                break;
            case "references":
                if (species != null) {
                    species.setReferences(referencesList);
                }
                break;
            case "reference":
                referencesList.add(reference);
                reference = null;
                break;
            case "images":
                if (species != null) {
                    species.setImages(imagesList);
                }
                break;
            case "image":
                imagesList.add(image);
                image = null;
                break;
            case "characteristics":
                if (species != null) {
                    species.setCharacteristics(characteristicsList);
                }
                break;
            case "characteristic":
                characteristicsList.add(characteristic);
                characteristic = null;
                break;
            case "conservationStatus":
                if (species != null) {
                    species.setConservationStatus(conservationStatus);
                    conservationStatus = null;
                }
                break;
            case "family":
                if (species != null) {
                    species.setFamily(family);
                    family = null;
                }
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(ch, start, length);
    }
}
