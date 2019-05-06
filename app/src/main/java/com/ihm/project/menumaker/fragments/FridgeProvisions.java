package com.ihm.project.menumaker.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Ingredients;


public class FridgeProvisions extends Fragment implements IListenItem2 {

    public static FridgeProvisions newInstance() {
        return new FridgeProvisions();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fridge_provisions, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        IngredientsListAdapter fridgeAdapter = new IngredientsListAdapter(getContext(), Ingredients.getAllIngredients());

        ListView provisions = layoutItem.findViewById(R.id.provisions);


        provisions.setAdapter(fridgeAdapter);
        fridgeAdapter.addListener(this);


        return view;
    }

    @Override
    public void onClickItem(Ingredient ingredient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(ingredient.getName());
        builder.show();
    }
}
