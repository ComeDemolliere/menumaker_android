package com.ihm.project.menumaker.models;

import android.content.Context;

import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.utils.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class Ingredients {
    private static  List<Ingredient> provisions = new ArrayList<>();
    private static  List<Ingredient> toBuyList = new ArrayList<>();

    //Just initialize all the ingredients we know
    public static void init(Context context){
        provisions.clear();
        toBuyList.clear();

        //init list
        JsonManager jsonManager = new JsonManager(context, "ingredientToBuy.json");
        toBuyList = jsonManager.getAllIngredientsFromJson();
        jsonManager = new JsonManager(context, "provision.json");
        provisions = jsonManager.getAllIngredientsFromJson();

    }

    //All the fridge's ingredients
    public static List<Ingredient> getProvisions(){
        return provisions;
    }

    //As his name said: to buy list :)
    public static List<Ingredient> getToBuyList(){
        return toBuyList;
    }

}
