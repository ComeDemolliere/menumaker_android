package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;

import java.util.ArrayList;
import java.util.List;

public class Dishes {
    private static List<Dish> dishes = new ArrayList<>();


    public static void init (){
        dishes.clear();
        dishes.add(new Dish("carbonara", R.drawable.carbonara, "blabla"));
        dishes.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishes.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishes.add(new Dish("risotto", R.drawable.risotto, "blabla"));
    }

    public static void add(Dish dish) {
        dishes.add(dish);
    }

    public static List<Dish> getDishes() {
        return dishes;
    }

    public static int size() { return dishes.size();}

}