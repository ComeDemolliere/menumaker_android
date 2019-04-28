package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dishes {
    private static List<Dish> dishes = new ArrayList<>();
    private static List<Dish> dishesEaten = new ArrayList<>();
    private static int pos = 0;


    public static void init (){
        //All dishes existing
        dishes.clear();
        dishes.add(new Dish("carbonara", R.drawable.carbonara, "blabla"));
        dishes.add(new Dish("risotto", R.drawable.risotto, "blabla"));

        //Dishes eaten
        dishesEaten.clear();
        dishesEaten.add(new Dish("carbonara", R.drawable.carbonara, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
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

    public static Dish getRandomDish() {
        Random rand = new Random();
        pos = rand.nextInt(dishes.size());
        return dishes.get(pos);
    }
}