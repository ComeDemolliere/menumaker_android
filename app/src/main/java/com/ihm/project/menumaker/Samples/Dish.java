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

    private int nbPeople = 1;

    private int nbCurrent = 1;

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
        this.fav = dish.getFav();
        this.nbPeople = dish.getNbPeople();
    }

    public void setDate(Date date) { this.date = date; }

    public String getName() {
        return name;
    }

    public String getImage() {return this.image; }

    public int getImageWithContext(Context context) {
        return context.getResources().getIdentifier(image, "drawable",  context.getPackageName());
    }

    public boolean getFav(){ return this.fav;}

    public void setFav(boolean fav) { this.fav = fav; }

    public Date getDate() {
        return date;
    }

    public String getReceipe() {
        return receipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setNbPeople(int nb){
        if(nbCurrent == 0) nbCurrent = nbPeople;
        if(nb > 0){
            System.out.println("la");
            ingredients.forEach(i -> i.setQuantity(((float)nb/nbCurrent) * i.getQuantity()));
            this.nbCurrent = nb;
        }
    }

    public int getNbPeople(){
        return this.nbPeople;
    }

    @Override
    public boolean equals(Object o) {
        Dish d = (Dish) o;
        return d.getName().equals(name);
    }

    public void setImage(String image) { this.image = image;}
}
