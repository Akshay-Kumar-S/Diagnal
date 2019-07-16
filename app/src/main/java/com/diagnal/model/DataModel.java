package com.diagnal.model;

public class DataModel {
    private String name;
    private String image;

    public DataModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
