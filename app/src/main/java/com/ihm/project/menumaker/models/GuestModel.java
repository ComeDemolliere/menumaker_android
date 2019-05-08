package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.Samples.Dish;

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

    public static class Guest {
        private String name;
        private List<Dish> dishesNotLiking;

        @Override
        public String toString() {
            return "Guest{" +
                    "name='" + name + '\'' +
                    ", dishesNotLiking=" + dishesNotLiking +
                    '}';
        }

        public Guest(String name, List<Dish> dishesNotLiking) {
            this.name = name;
            this.dishesNotLiking = dishesNotLiking;
        }


        public String getName() {
            return name;
        }
    }
}
