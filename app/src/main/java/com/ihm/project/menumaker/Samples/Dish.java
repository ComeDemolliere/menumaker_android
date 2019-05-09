package com.ihm.project.menumaker.Samples;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Dish {
    private String name;

    private String image;

    private Date date;

    private String receipe;

    private List<Ingredient> ingredients;

    private boolean fav = false;

    public Dish(String name, String image, String receipe, List<Ingredient> ingredients) {
        this.name = name;
        this.image = image;
        this.receipe = receipe;
        this.ingredients = ingredients;
    }

    public Dish(Dish dish){
        this.name = dish.getName();
        this.image = dish.getImage();
        this.date = dish.getDate();
        this.receipe = dish.getReceipe();
        this.ingredients = dish.getIngredients();
    }

    public void setDate(Date date) { this.date = date; }

    public String getName() {
        return name;
    }

    public String getImage() {return this.image;}

    public int getImageWithContext(Context context) {
        return context.getResources().getIdentifier(image, "drawable",  context.getPackageName());
    }

    public boolean getFav(){ return this.fav;}

    public void setFav(boolean fav) { this.fav = fav;}

    public Date getDate() {
        return date;
    }

    public String getReceipe() {
        return receipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        Dish d = (Dish) o;
        return d.getName().equals(name);
    }
}
