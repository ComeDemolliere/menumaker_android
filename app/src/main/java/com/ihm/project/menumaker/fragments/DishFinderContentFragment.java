package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.models.Dishes;

public class DishFinderContentFragment extends Fragment {

    public static DishFinderContentFragment newInstance(int pos) {
        DishFinderContentFragment f = new DishFinderContentFragment();

        Bundle bdl = new Bundle(1);

        bdl.putInt("EXTRA_DISH_POS", pos);

        f.setArguments(bdl);

        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_finder_content_fragment, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        ImageView image = layoutItem.findViewById(R.id.dishImage);
        TextView name = layoutItem.findViewById(R.id.dishName);
        TextView recipe = layoutItem.findViewById(R.id.dishRecipe);

        if(getArguments() != null){
            Dish dish = Dishes.getDish(getArguments().getInt("EXTRA_DISH_POS"));
            image.setImageResource(dish.getImage());
            name.setText(dish.getName());
            recipe.setText(dish.getReceipe());
        }

        return view;
    }
}
