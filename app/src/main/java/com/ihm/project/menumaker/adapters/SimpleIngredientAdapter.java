package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;

import java.util.List;

public class SimpleIngredientAdapter extends BaseAdapter {
    private List<Ingredient> ingredients;
    private LayoutInflater mInflater;

    public SimpleIngredientAdapter(Context context, List<Ingredient> listView){
        this.ingredients = listView;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.ingredient_normal, parent, false);

        TextView name = layoutItem.findViewById(R.id.name_ingredient1);
        TextView quantity = layoutItem.findViewById(R.id.quantity_ingredient1);

        Ingredient ingredient = ingredients.get(position);

        name.setText(ingredient.getName());
        quantity.setText(ingredient.getQuantity() + " " + ingredient.show());

        layoutItem.setTag(position);

        return layoutItem;
    }
}
