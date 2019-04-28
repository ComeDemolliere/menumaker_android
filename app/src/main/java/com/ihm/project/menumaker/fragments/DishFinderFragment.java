package com.ihm.project.menumaker.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.DishesAdapter;
import com.ihm.project.menumaker.models.Dishes;

public class DishFinderFragment extends Fragment {

    public static DishFinderFragment newInstance() {
        return new DishFinderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_finder_fragment, container, false);

        Dish currentDish = Dishes.getRandomDish();

        RelativeLayout layoutItem = (RelativeLayout) view;

        ImageView image = layoutItem.findViewById(R.id.dishImage);
        TextView name = layoutItem.findViewById(R.id.dishName);

        Dish dish = Dishes.getRandomDish();

        image.setImageResource(dish.getImage());
        name.setText(dish.getName());
        return view;
    }

}
