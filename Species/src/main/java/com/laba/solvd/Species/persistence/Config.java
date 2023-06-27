package com.laba.solvd.Species.persistence;

import java.util.Properties;

public enum Config {
    DRIVER,
    URL,
    USERNAME,
    PASSWORD,
    POOLSIZE;
    private static final String PATH = "Species/src/main/resources/config.properties";
    private static Properties properties;
    private String value;

    private void init() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(Config.class.getResourceAsStream(PATH));
            }
            catch (Exception e) {
                System.exit(1);
            }
        }
        value = (String) properties.get(this.toString());
    }

    public String getValue() {
        if (value == null) {
            init();
        }
        return value;
    }

}
