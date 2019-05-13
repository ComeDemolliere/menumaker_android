package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.fragments.dish.DishRepFragment;
import com.ihm.project.menumaker.fragments.home.HomeFragment;
import com.ihm.project.menumaker.models.Dishes;

import java.io.File;
import java.util.List;

public class FavAndSugDishRVAdapter extends RecyclerView.Adapter<DishHolder> {

    private List<Dish> dishes;
    private Context context;
    private FragmentTransaction transaction;
    private View parent;

    public FavAndSugDishRVAdapter(Context context, List<Dish> dishes, FragmentTransaction transaction){
        this.context = context;
        this.dishes = dishes;
        this.transaction = transaction;
    }

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        this.parent = viewGroup;
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favorite_dishes_layout, viewGroup, false);


        return new DishHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder dishHolder, int i) {
        Dish dish = dishes.get(i);
        dishHolder.getDishName().setText(dish.getName());

        Bitmap bm;

        File imgFile = new  File(dish.getImage());

        if(imgFile.exists()){
            bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else{
            int ressource1 = dish.getImageWithContext(context);
            bm = BitmapFactory.decodeResource(parent.getResources(), ressource1);
        }

        dishHolder.getDishImage().setImageBitmap(bm);

        dishHolder.getDishName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicMethod(i);
            }
        });

        dishHolder.getDishImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicMethod(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void updateList(List<Dish> dishes){
        this.dishes = dishes;
        this.notifyDataSetChanged();
    }

    private void onClicMethod(int i) {
        Dishes.setCurrentDish(dishes.get(i));
        //move to your fragment
        transaction.replace(R.id.main_container, new DishRepFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
