package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.models.Ingredients;
import com.ihm.project.menumaker.utils.IngredientsType;

public class IngredientAddingToBuyList extends Fragment implements View.OnClickListener {
    Spinner spinner;
    IngredientsType ingtype;
    EditText nameofIngredient;
    EditText quantity;
    String spi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.addding_ingredient_tobuylist, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        spinner = layoutItem.findViewById(R.id.spinner2);

        String[] ingredientsTypes= {"g", "L", "u"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ingredientsTypes);

        nameofIngredient = (EditText) layoutItem.findViewById (R.id.name_to_add2);

        quantity = (EditText) layoutItem.findViewById (R.id.quantity2);


        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //spinner.setOnItemSelectedListener(this);

        spi= (String)spinner.getSelectedItem();

        Button addbuton= (Button) layoutItem.findViewById(R.id.button1_add_ingredient);

        addbuton.setOnClickListener(this);


        return view;
    }



    @Override
    public void onClick(View v) {
        if (spi.equals("g")) {
            Ingredients.getToBuyList().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.POUNDABLE, Integer.parseInt(quantity.getText().toString())));
        }

        if (spi.equals("L")) {
            Ingredients.getToBuyList().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.LIQUIDE, Integer.parseInt(quantity.getText().toString())));
        }

        if (spi.equals("u")) {
            Ingredients.getToBuyList().add(new Ingredient(nameofIngredient.getText().toString(), IngredientsType.COUNTABLE, Integer.parseInt(quantity.getText().toString())));
        }
    }
}

