package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.fragments.IListenItem2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientsListAdapter extends BaseAdapter implements Filterable {
    private List<Ingredient> ingredients;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private IListenItem2 listViewListen2;

    public IngredientsListAdapter(Context context, List<Ingredient> listView){
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
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.ingredient_layout, parent, false);

        TextView name = layoutItem.findViewById(R.id.name_ingredient);
        TextView quantity = layoutItem.findViewById(R.id.quantity_ingredient);

        Ingredient ingredient = ingredients.get(position);

        name.setText(ingredient.getName());
        quantity.setText(ingredient.getQuantity() + " " + ingredient.show());

        layoutItem.setTag(position);

        return layoutItem;
    }

    public void addListener(IListenItem2 itemToListen) {
        listViewListen2 = itemToListen;
    }

    private Filter filter;
    private List<Ingredient> mOriginalValues;

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,FilterResults results)
                {

                    ingredients = (List<Ingredient>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    List<Ingredient> filtredArray = new ArrayList<Ingredient>();


                    if (mOriginalValues == null)
                    {
                        mOriginalValues = new ArrayList<Ingredient>(ingredients); // saves the original data in mOriginalValues
                    }

                    if (constraint == null || constraint.length() == 0)
                    {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    }
                    else
                    {
                        final CharSequence prefix = constraint.toString().toLowerCase();
                        filtredArray = ingredients.stream().filter(elem -> elem.getName().toLowerCase().startsWith(prefix.toString())).collect(Collectors.toList());
                        // set the Filtered result to return
                        results.count = filtredArray.size();
                        results.values = filtredArray;
                    }
                    return results;
                }
            };
        }
        return filter;
    }
}
