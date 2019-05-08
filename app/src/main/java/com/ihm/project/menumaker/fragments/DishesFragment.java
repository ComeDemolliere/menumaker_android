package com.ihm.project.menumaker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.FavoriteAndSuggestedDishesAdapter;
import com.ihm.project.menumaker.models.Dishes;

public class DishesFragment extends Fragment {
    private FavoriteAndSuggestedDishesAdapter favoriteDishesAdapter;
    private Activity activity;

    public static DishesFragment newInstance() {
        return new DishesFragment();
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.dishes_fragment, container, false);

        favoriteDishesAdapter = new FavoriteAndSuggestedDishesAdapter(getContext(), Dishes.getFavoriteDishes());

        return  view;
    }



}
