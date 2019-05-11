package com.ihm.project.menumaker.fragments.guests;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ihm.project.menumaker.MainActivity;
import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.GuestModel;
import com.ihm.project.menumaker.utils.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class CreateGuestFragment extends Fragment {

    private String name = "";

    public void setName(String name) {
        this.name = name;
    }

    private EditText nameEditText;
    private EditText searchIngrdient;
    private ListView listView;
    private List<Ingredient> ingredients;
    private List<Ingredient> notLikingIngredients = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_guest_layout,
                container, false);

        nameEditText = v.findViewById(R.id.nameGuest);
        nameEditText.setText(this.name);

        searchIngrdient = v.findViewById(R.id.searchIngredient);


        listView = v.findViewById(R.id.ingredientsListView);

        JsonManager jsonManager = new JsonManager(getContext(), "ingredients.json");

        ingredients = jsonManager.getEveryIngredients();
        IngredientsListAdapter adapter = new IngredientsListAdapter(getContext(), ingredients);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingredient ingredient = ingredients.get(position);
                if (!notLikingIngredients.contains(ingredient)) {
                    view.setBackgroundColor(Color.parseColor("#5668EC"));
                    notLikingIngredients.add(ingredients.get(position));

                } else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    notLikingIngredients.remove(ingredients.get(position));
                }
            }
        });

        searchIngrdient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button addGuestButton = v.findViewById(R.id.addGuestButton);
        addGuestButton.setOnClickListener(event -> {
            GuestModel.addGuest(new GuestModel.Guest(nameEditText.getText().toString(), notLikingIngredients));
            MainActivity mainActivity = (MainActivity) getContext();
            mainActivity.openDishesFragment();
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        nameEditText.setText(this.name);
    }
}
