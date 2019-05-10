package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.models.Dishes;
import com.ihm.project.menumaker.models.Ingredients;
import com.ihm.project.menumaker.utils.IngredientsType;

public class IngredientAddingProvision extends Fragment implements View.OnClickListener {
    Spinner spinner;
    IngredientsType ingtype;
    EditText nameofIngredient;
    EditText quantity;
    String spi;
    int ingredienType;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.adding_ingredient_provisions, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        spinner = layoutItem.findViewById(R.id.spinner);

        String[] ingredientsTypes= {"g", "L", "u"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ingredientsTypes);

        nameofIngredient = (EditText) layoutItem.findViewById (R.id.name_to_add);

        quantity = (EditText) layoutItem.findViewById (R.id.quantity);


        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //spinner.setOnItemSelectedListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ingredienType=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ingredienType=0;
            }
        });

        System.out.println(spi);

        Button addbuton= (Button) layoutItem.findViewById(R.id.button2_add_ingredient);

        addbuton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

            if(ingredienType==0)  {
                Ingredients.getProvisions().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.POUNDABLE, Integer.parseInt(quantity.getText().toString())));
            }

            if(ingredienType==1) {
                Ingredients.getProvisions().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.LIQUIDE, Integer.parseInt(quantity.getText().toString())));
            }

          if(ingredienType==2){
                Ingredients.getProvisions().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.COUNTABLE, Integer.parseInt(quantity.getText().toString())));
            }

        getFragmentManager().popBackStackImmediate();
        }




}
