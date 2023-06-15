package com.laba.solvd.Species.domain;

public class Species {
    private int id;
    private String commonName;
    private String scientificName;
    ConservationStatuses conservationStatus = new ConservationStatuses();
    private int conservationStatusID;
    Taxonomies taxonomy = new Taxonomies();
    private String[] taxonomyList = {taxonomy.getKingdom()[1], taxonomy.getItsClass()[1], taxonomy.getFamily()[1]};

    public Species(int id, String commonName, String scientificName, int conservationStatusID, String[] taxonomyList) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.conservationStatusID = conservationStatus.getId();
        this.taxonomyList = taxonomyList;
    }

    public Species() {
    }

    public String getCommonName() {
        return commonName;
    }
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getScientificName() {
        return scientificName;
    }
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String[] getTaxonomy() {
        return taxonomyList;
    }

    public int getFamilyID() {
        return Integer.valueOf(taxonomy.getFamily()[0]);
    }

    public void setTaxonomy(String[] taxonomyList) {
        this.taxonomyList = taxonomyList;
    }

    public int getConservationStatusID() {
        return conservationStatusID;
    }

    public void setConservationStatusID(int conservationStatusID) {
        this.conservationStatusID = conservationStatus.getId();
    }
}
