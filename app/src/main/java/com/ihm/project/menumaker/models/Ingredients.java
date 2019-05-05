package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.utils.IngredientsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Ingredients {
    private static HashMap<Ingredient, Integer> allIngredients = new HashMap<>();
    private static  HashMap<Ingredient, Integer> provisions = new HashMap<>();
    private static  HashMap<Ingredient, Integer> toBuyList = new HashMap<>();

    //Just initialize all the ingredients we know
    public static void init(){
        allIngredients.clear();
        allIngredients.put(new Ingredient("tomato", R.drawable.tomato, IngredientsType.COUNTABLE), 2);

    }

    //All the ingredients list
    public static List<Ingredient> getAllIngredients(){
        List<Ingredient> allIngredients2= new ArrayList<>();
        allIngredients2.addAll(allIngredients.keySet());
        return allIngredients2;
    }

    //All the fridge's ingredients
    public static Set<Ingredient> getProvisions(){
        return provisions.keySet();
    }

    //As his name said: to buy list :)
    public static Set<Ingredient> getToBuyList(){
        return toBuyList.keySet();
    }

    public static int getQuantityByNameinAllIngredients(String name){
        Set<Ingredient> allIngredientsName = allIngredients.keySet();

        for ( Ingredient in: allIngredientsName){
            if(in.getName().equals(name)) return allIngredients.get(in);


        }
        return 0;
    }

    public static Ingredient getIngredientInAllIngredients(String name){
        Set<Ingredient> allIngredientsName = allIngredients.keySet();

        for ( Ingredient in: allIngredientsName){
            if(in.getName().equals(name)) return in;


        }
        return null;
    }


}
