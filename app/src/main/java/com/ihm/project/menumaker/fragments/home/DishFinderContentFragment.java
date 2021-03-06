package com.ihm.project.menumaker.fragments.home;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.SimpleIngredientAdapter;
import com.ihm.project.menumaker.models.Dishes;

import java.io.File;

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
        Switch swi = layoutItem.findViewById(R.id.switch1);
        EditText nombreDeConvives = layoutItem.findViewById(R.id.numberOfPeopleSpinner);


        Button button = layoutItem.findViewById(R.id.button2);

        if(getArguments() != null){
            dish = Dishes.getDishes().get(getArguments().getInt("EXTRA_DISH_POS"));

            nombreDeConvives.setText("" + dish.getNbPeople());

            Bitmap bm;

            File imgFile = new  File(dish.getImage());

            if(imgFile.exists()){
                bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
            else{
                int ressource = dish.getImageWithContext(getContext());
                bm = BitmapFactory.decodeResource(getResources(), ressource);
            }

            image.setImageBitmap(bm);
            name.setText(dish.getName());
            swi.setChecked(dish.getFav());
            recipe.setText(dish.getReceipe());
            nombreDeConvives.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() >= 1){
                        dish.setNbPeople(Integer.parseInt(s.toString().substring(s.toString().length() - 1)));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) Dishes.addDishToFav(dish);
                else Dishes.rmDishToFav(dish);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { createDialog(); }
        });


        return view;
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Ingrédients");
        SimpleIngredientAdapter adapter = new SimpleIngredientAdapter(builder.getContext(), dish.getIngredients());
        builder.setAdapter(adapter, null);
        builder.setPositiveButton("Retour", null);
        builder.show();
    }
}
