package com.ihm.project.menumaker.models;

import android.media.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dishes {
    private static List<String> dishesName = new ArrayList<>();


    public static void init (){
        dishesName.clear();
        dishesName.add("carbonara");
        dishesName.add("risotto");
    }

    public static void add(String name) {
        dishesName.add(name);
    }

    public static List<String> getDishesName() {
        return dishesName;
    }

    public static int size() { return dishesName.size();}

}
