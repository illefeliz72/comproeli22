package com.artvantage.models;

import java.io.Serializable;

public class ArtProject implements Serializable {
    private String title;
    private double price;
    private byte[] imageBytes; // This holds the actual picture data

    public ArtProject(String title, double price, byte[] imageBytes) {
        this.title = title;
        this.price = price;
        this.imageBytes = imageBytes;
    }

    public String getTitle() {
        return title;
    }
}