package com.ihm.project.menumaker.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Dishes;

public class DishFinderContentFragment extends Fragment {

    private Dish dish;

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

        Button button = layoutItem.findViewById(R.id.button2);

        if(getArguments() != null){
            dish = Dishes.getDishes().get(getArguments().getInt("EXTRA_DISH_POS"));
            image.setImageResource(dish.getImageWithContext(getContext()));
            name.setText(dish.getName());
            recipe.setText(dish.getReceipe());
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { createDialog(); }
        });

        return view;
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Ingrédients");
        IngredientsListAdapter adapter = new IngredientsListAdapter(builder.getContext(), dish.getIngredients());
        builder.setAdapter(adapter, null);
        builder.setPositiveButton("Retour", null);
        builder.show();
    }
}
