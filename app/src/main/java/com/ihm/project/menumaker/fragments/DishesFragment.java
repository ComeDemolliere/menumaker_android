package com.ihm.project.menumaker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.FavAndSugDishRVAdapter;
import com.ihm.project.menumaker.models.Dishes;

public class DishesFragment extends Fragment {
    private FavAndSugDishRVAdapter favAdapter;
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

        RecyclerView favList = view.findViewById(R.id.favList);
        favAdapter = new FavAndSugDishRVAdapter(getContext(), Dishes.getDishes(), getFragmentManager().beginTransaction());

        //orientation
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        //set  my recyclerView
        favList.setLayoutManager(llm);
        favList.setAdapter(favAdapter);
        return  view;
    }



}
