package com.ihm.project.menumaker.Samples;

import java.util.Date;
import java.util.List;

public class Dish {
    private String name;
    private int image;
    private Date date;
    private String receipe;
    private List ingredients;

    public Dish(String name, int image, String receipe) {
        this.name = name;
        this.image = image;
        //this.date = date;
        this.receipe = receipe;
        //this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public String getReceipe() {
        return receipe;
    }

    public List getIngredients() {
        return ingredients;
    }
}
