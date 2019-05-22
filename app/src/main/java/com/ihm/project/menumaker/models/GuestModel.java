package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class GuestModel {

    private static List<Guest>  guests = new ArrayList<Guest>();
    public static void addGuest(Guest guest) {
        guests.add(guest);
    }
    public static List<Guest> getGuests() {
        return guests;
    }
    public static void deleteGuestByIndex(int position) {
        guests.remove(position);
    }


    private static List<Guest>  selectedGuest = new ArrayList<>();

    public static void selectGuest(Guest guest) {
        selectedGuest.add(guest);
    }

    public static void deleteSelectedGuest(Guest guest) {
        selectedGuest.remove(guest);
    }

    public static class Guest {
        private String name;
        private List<Ingredient> ingredientsNotLiking;

        @Override
        public String toString() {
            return "Guest{" +
                    "name='" + name + '\'' +
                    ", ingredientsNotLiking=" + ingredientsNotLiking +
                    '}';
        }

        public Guest(String name, List<Ingredient> ingredientsNotLiking) {
            this.name = name;
            this.ingredientsNotLiking = ingredientsNotLiking;
        }


        public String getName() {
            return name;
        }

        public List<Ingredient> getNotLikingsIngredients() {
            return this.ingredientsNotLiking;
        }
    }
}
