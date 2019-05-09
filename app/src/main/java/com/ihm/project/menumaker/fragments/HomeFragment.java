package com.ihm.project.menumaker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.DishesAdapter;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Dishes;

public class HomeFragment extends Fragment implements IListenItem{

    //private ListView dishes;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        DishesAdapter dishesAdapter = new DishesAdapter(getContext(), Dishes.getDishesEaten());

        ListView dishes = view.findViewById(R.id.dishes);

        dishes.setAdapter(dishesAdapter);
        dishesAdapter.addListener(this);

        return view;
    }

    @Override
    public void onClickItem(Dish dish) {
        AlertDialog.Builder builderR = new AlertDialog.Builder(getContext());
        builderR.setTitle(dish.getName());
        builderR.setMessage(dish.getReceipe());
        builderR.setPositiveButton("Ingrédients", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(dish.getName());
                IngredientsListAdapter adapter = new IngredientsListAdapter(builder.getContext(), dish.getIngredients());
                builder.setAdapter(adapter, null);
                builder.setPositiveButton("Recette", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builderR.show();
                    }
                });
                builder.show();
            }
        });
        builderR.show();
    }
}
