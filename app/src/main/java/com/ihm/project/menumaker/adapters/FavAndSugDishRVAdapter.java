package com.ihm.project.menumaker.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.fragments.HomeFragment;
import com.ihm.project.menumaker.models.Dishes;

import java.util.List;

public class FavAndSugDishRVAdapter extends RecyclerView.Adapter<DishHolder> {

    private List<Dish> dishes;
    private Context context;
    private FragmentTransaction transaction;

    public FavAndSugDishRVAdapter(Context context, List<Dish> dishes, FragmentTransaction transaction){
        this.context = context;
        this.dishes = dishes;
        this.transaction = transaction;
    }

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favorite_dishes_layout, viewGroup, false);

        //move on click on item
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dishes.setCurrentDish(dishes.get(pos));
                //move to your fragment
                transaction.replace(R.id.container, new HomeFragment());
                transaction.commit();
            }
        });

        return new DishHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder dishHolder, int i) {
        dishHolder.getDishName().setText(dishes.get(i).getName());
        dishHolder.getDishImage().setImageResource(dishes.get(i).getImageWithContext(context));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
