package com.artvantage.models;

import java.io.Serializable;

public abstract class ArtProject implements Serializable {
    private String title;
    private double price;

    public ArtProject(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getWorkType();
}