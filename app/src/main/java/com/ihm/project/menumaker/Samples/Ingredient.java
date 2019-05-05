package com.ihm.project.menumaker.Samples;

import com.ihm.project.menumaker.utils.IngredientsType;

public class Ingredient {

    private String name;
    private int image;
    private IngredientsType ingredientsType;


    public Ingredient(String name, int image, IngredientsType ingredientsType){
        this.image=image;
        this.name=name;
        this.ingredientsType=ingredientsType;
    }

    public String getName(){
        return this.name;
    }

    public IngredientsType getIngredientsType(){
        return this.ingredientsType;
    }

    public int getImage() {
        return image;
    }

    //To show an ingredient in function of its type
    public String show(){

        if(this.ingredientsType== IngredientsType.POUNDABLE) return  "g";

        if(this.ingredientsType== IngredientsType.LIQUIDE) return  "l";

        return " ";
    }

}