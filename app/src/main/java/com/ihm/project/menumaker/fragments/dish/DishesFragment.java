package com.ihm.project.menumaker.fragments.dish;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.FavAndSugDishRVAdapter;
import com.ihm.project.menumaker.models.Dishes;

import java.util.ArrayList;
import java.util.List;

public class DishesFragment extends Fragment {
    private FavAndSugDishRVAdapter favAdapter;
    private FavAndSugDishRVAdapter sugAdapter;
    private FavAndSugDishRVAdapter allAdapter;

    private List<Dish> favs;
    private List<Dish> sugs;
    private List<Dish> all;


    public static DishesFragment newInstance() {
        return new DishesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.dishes_fragment, container, false);

        RecyclerView favList = view.findViewById(R.id.favList);
        RecyclerView sugList = view.findViewById(R.id.sugList);
        RecyclerView allList = view.findViewById(R.id.allList);

        SearchView searchView = view.findViewById(R.id.search);

        initList();

        //init Adapter
        favAdapter = new FavAndSugDishRVAdapter(getContext(), favs, getFragmentManager().beginTransaction());
        sugAdapter = new FavAndSugDishRVAdapter(getContext(), sugs, getFragmentManager().beginTransaction());
        allAdapter = new FavAndSugDishRVAdapter(getContext(), all, getFragmentManager().beginTransaction());

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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                favs = Dishes.filter(Dishes.getFavoriteDishes(), s);
                sugs = Dishes.filter(Dishes.getSuggestedDishes(), s);
                all = Dishes.filter(Dishes.getDishes(), s);

                favAdapter.updateList(favs);
                sugAdapter.updateList(sugs);
                allAdapter.updateList(all);

                System.out.println(all);
                return false;
            }
        });

        return  view;
    }

    private void initList(){
        favs = new ArrayList<>(Dishes.getFavoriteDishes());
        sugs = new ArrayList<>(Dishes.getSuggestedDishes());
        all = new ArrayList<>(Dishes.getDishes());
    }


}
