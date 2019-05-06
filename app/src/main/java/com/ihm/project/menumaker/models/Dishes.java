package com.ihm.project.menumaker.models;

import android.content.Context;

import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.utils.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class Dishes {
    private static List<Dish> dishes = new ArrayList<>();
    private static List<Dish> dishesEaten = new ArrayList<>();
    private static int pos = 0;
    private static Dish currentDish;


    public static void init(Context context){
        currentDish = null;
        JsonManager jsonManager = new JsonManager(context, "dish.json");
        dishes = jsonManager.getAllDishesFromJSON();
    }

    public static void add(Dish dish) {
        dishes.add(dish);
    }

    public static List<Dish> getDishes() {
        return dishes;
    }

    public static List<Dish> getDishesEaten() {
        return dishesEaten;
    }

    public static int size() { return dishes.size();}

    public static void eatDish(){
        if(currentDish != null){
            dishesEaten.add(currentDish);
        }
    }

    public static Dish getCurrentDish(){
        return currentDish;
    }

    public static void setCurrentDish(Dish d){
        currentDish = new Dish(d);
    }
}