package com.ihm.project.menumaker.Samples;

import com.ihm.project.menumaker.utils.IngredientsType;

public class Ingredient {

    private String name;
    private IngredientsType ingredientsType;
    private int quantity;


    public Ingredient(String name, IngredientsType ingredientsType, int quantity){
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

    public int getQuantity(){
        return this.quantity;
    }

    //To show an ingredient in function of its type
    public String show(){

        if(this.ingredientsType== IngredientsType.POUNDABLE) return  "g";

        if(this.ingredientsType== IngredientsType.LIQUIDE) return  "L";

        return " ";
    }

}