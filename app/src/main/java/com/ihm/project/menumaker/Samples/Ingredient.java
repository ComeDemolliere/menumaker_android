package com.ihm.project.menumaker.Samples;

import com.ihm.project.menumaker.utils.IngredientsType;

public class Ingredient {

    private String name;
    private IngredientsType ingredientsType;


    public Ingredient(String name, IngredientsType ingredientsType){
        this.name=name;
        this.ingredientsType=ingredientsType;
    }

    public String getName(){
        return this.name;
    }

    public IngredientsType getIngredientsType(){
        return this.ingredientsType;
    }

    //To show an ingredient in function of its type
    public String show(){

        if(this.ingredientsType== IngredientsType.POUNDABLE) return  "g";

        if(this.ingredientsType== IngredientsType.LIQUIDE) return  "l";

        return " ";
    }

}