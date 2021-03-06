package com.ihm.project.menumaker.utils;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private Context context;
    private String file;

    public JsonManager(Context context, String file){
        this.context = context;
        this.file = file;
    }

    public ArrayList<Dish> getAllDishesFromJSON() {
        ArrayList<Dish> dishes = new ArrayList<Dish>();

        try {
            JSONArray jsonArray = new JSONArray(getJSONFromAsset(context, this.file));
            Type listType = new TypeToken<ArrayList<Dish>>(){}.getType();

            dishes = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    public List<Ingredient> getAllIngredientsFromJson() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try {
            JSONArray jsonArray = new JSONArray(getJSONFromAsset(context, this.file));
            Type listType = new TypeToken<ArrayList<Ingredient>>(){}.getType();

            ingredients = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public List<Ingredient> getEveryIngredients() {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
            try {
                JSONArray jsonArray = new JSONArray(getJSONFromAsset(context, this.file));
                Type listType = new TypeToken<List<Ingredient>>(){}.getType();
                ingredients = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return ingredients;
    }

    private  String getJSONFromAsset(Context context, String file) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}

