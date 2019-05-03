package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.ListView;
>>>>>>> parent of a090c07... Fridge evolution with some buggs

import com.ihm.project.menumaker.R;

public class FridgeFragment extends Fragment {

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }

<<<<<<< HEAD
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
=======
    private TabLayout provisions;
    private TabLayout shoppinglist;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View fridgeView = inflater.inflate(R.layout.home_fragment, container, false);

        ListView myIngredients = fridgeView.findViewById(R.id.provisions);
        ListView myShoppingList = fridgeView.findViewById(R.id.shopping_list);
>>>>>>> parent of a090c07... Fridge evolution with some buggs
        return inflater.inflate(R.layout.fridge_fragment, container, false);
    }

}
