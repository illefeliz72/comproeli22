package com.artvantage.models;

import java.io.Serializable;

public abstract class ArtProject implements Serializable {
    private String title;
    private double price;
    private byte[] imageData;

    public ArtProject(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setImageData(byte[] data) {
        this.imageData = data;
    }

    public byte[] getImageData() {
        return imageData;
    }
}