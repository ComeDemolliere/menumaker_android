package com.ihm.project.menumaker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihm.project.menumaker.R;

public class DishHolder extends RecyclerView.ViewHolder {

    private TextView dishName;
    private ImageView dishImage;

    public DishHolder(@NonNull View itemView) {
        super(itemView);
        dishName = itemView.findViewById(R.id.nameDish);
        dishImage = itemView.findViewById(R.id.imageDish);
    }

    public ImageView getDishImage(){
        return dishImage;
    }

    public TextView getDishName(){
        return dishName;
    }
}
