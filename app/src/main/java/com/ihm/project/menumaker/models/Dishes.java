package com.ihm.project.menumaker.models;

import android.content.Context;

import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.utils.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class Dishes {
    private static List<Dish> dishes = new ArrayList<>();
    private static List<Dish> favoriteDishes = new ArrayList<>();
    private static List<Dish> suggestedDishes = new ArrayList<>();
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

    public static List<Dish> getFavoriteDishes() { return favoriteDishes; }
    public static List<Dish> getSuggestedDishes() {return suggestedDishes; }

    public static int size() { return dishes.size();}

    public static void eatDish(){
        if(currentDish != null){
            dishesEaten.add(currentDish);
            if(!suggestedDishes.contains(currentDish))
                suggestedDishes.add(currentDish);

            for (Ingredient i: currentDish.getIngredients()) {
                if(Ingredients.getProvisions().contains(i)){
                    Ingredient tmp = Ingredients.rmIngToProvisions(i);
                    if(tmp!=null){
                        Ingredients.addIngToBuyList(tmp);
                    }
                }else {
                    Ingredients.addIngToBuyList(i);
                }
            }
        }
    }

    public static void addDishToFav(Dish d) {
        favoriteDishes.add(d);
        d.setFav(true);
    }

    public static void rmDishToFav(Dish d) {
        d.setFav(false);
        favoriteDishes.remove(d);
    }

    public static Dish getCurrentDish(){
        return currentDish;
    }

    public static void setCurrentDish(Dish d){
        currentDish = new Dish(d);
    }

    public static List<Dish> filter(List<Dish> dishList, String filter){
        String f = filter.toLowerCase();
        System.out.println(f);
        List<Dish> res = new ArrayList<>();
        for (Dish d: dishList) {
            if(d.getName().toLowerCase().contains(f)) {
                res.add(d);
            }
        }
        return res;
    }
}