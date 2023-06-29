package com.laba.solvd.Species.domain;

public class CharacteristicBuilder {
    private Integer id;
    private String name;
    private String category;

    public CharacteristicBuilder() {
    }

    public CharacteristicBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public CharacteristicBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacteristicBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public Characteristic getCharacteristic() {
        return new Characteristic(id, name, category);
    }
}
