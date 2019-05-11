package com.ihm.project.menumaker.Samples;

import com.google.gson.Gson;
import com.ihm.project.menumaker.utils.IngredientsType;

import java.util.List;

public class Ingredient {

    private String name;
    private IngredientsType ingredientsType;
    private float quantity;


    public Ingredient(String name, IngredientsType ingredientsType, float quantity){
        this.name=name;
        this.ingredientsType=ingredientsType;
        this.quantity = quantity;
    }


    public String getName(){
        return this.name;
    }

    public IngredientsType getIngredientsType(){
        return this.ingredientsType;
    }

    public float getQuantity(){
        return this.quantity;
    }

    //To show an ingredient in function of its type
    public String show(){

        if(this.ingredientsType== IngredientsType.POUNDABLE) return  "g";

        if(this.ingredientsType== IngredientsType.LIQUIDE) return  "L";

        return " ";
    }

    @Override
    public boolean equals(Object obj) {
        Ingredient ingredient = (Ingredient) obj;
        return name.toLowerCase().equals(ingredient.getName().toLowerCase());
    }

    public void setQuantity(float quantity){
        this.quantity = quantity;
    }
}