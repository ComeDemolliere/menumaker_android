package com.ihm.project.menumaker.fragments;

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


public class FridgeToBuyList extends Fragment implements IListenItem2 {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fridge_tobuylist, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        IngredientsListAdapter fridgeAdapter = new IngredientsListAdapter(getContext(), Ingredients.getToBuyList());

        ListView toBuyList = layoutItem.findViewById(R.id.tobuylist);


        toBuyList.setAdapter(fridgeAdapter);
        fridgeAdapter.addListener(this);


        return view;
    }

    @Override
    public void onClickItem(Ingredient ingredient) {

    }
}