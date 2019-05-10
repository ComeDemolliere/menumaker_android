package com.ihm.project.menumaker.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Ingredients;

import java.util.ArrayList;
import java.util.List;


public class FridgeProvisions extends Fragment {

    ListView provisions;
    List provisons2=Ingredients.getProvisions();
    IngredientsListAdapter fridgeAdapter;

    public static FridgeProvisions newInstance() {
        return new FridgeProvisions();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fridge_provisions, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        fridgeAdapter = new IngredientsListAdapter(getContext(), Ingredients.getProvisions());

        provisions = layoutItem.findViewById(R.id.provisions);


        provisions.setAdapter(fridgeAdapter);


        return view;
    }


}