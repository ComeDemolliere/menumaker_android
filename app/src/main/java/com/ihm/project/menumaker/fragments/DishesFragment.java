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
    private FavAndSugDishRVAdapter sugAdapter;
    private FavAndSugDishRVAdapter allAdapter;
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
        RecyclerView sugList = view.findViewById(R.id.sugList);
        RecyclerView allList = view.findViewById(R.id.allList);

        //init Adapter
        favAdapter = new FavAndSugDishRVAdapter(getContext(), Dishes.getFavoriteDishes(), getFragmentManager().beginTransaction());
        sugAdapter = new FavAndSugDishRVAdapter(getContext(), Dishes.getDishes(), getFragmentManager().beginTransaction());
        allAdapter = new FavAndSugDishRVAdapter(getContext(), Dishes.getDishes(), getFragmentManager().beginTransaction());

        //orientation
        LinearLayoutManager llm1 = new LinearLayoutManager(getContext());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm2 = new LinearLayoutManager(getContext());
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager llm3 = new LinearLayoutManager(getContext());
        llm3.setOrientation(LinearLayoutManager.HORIZONTAL);

        //set  my recyclerView
        favList.setLayoutManager(llm1);
        sugList.setLayoutManager(llm2);
        allList.setLayoutManager(llm3);
        favList.setAdapter(favAdapter);
        sugList.setAdapter(sugAdapter);
        allList.setAdapter(allAdapter);

        return  view;
    }



}
