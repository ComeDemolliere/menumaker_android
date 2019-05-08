package com.ihm.project.menumaker.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.MainActivity;
import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.fragments.DishesFragment;
import com.ihm.project.menumaker.fragments.HomeFragment;
import com.ihm.project.menumaker.fragments.IListenItem;
import com.ihm.project.menumaker.models.Dishes;

import java.util.Date;
import java.util.List;

public class FavoriteAndSuggestedDishesAdapter extends BaseAdapter {
    private List<Dish> listView;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private IListenItem listViewListen;
    private Activity activity;
    private DishesFragment dishesFragment;

    public FavoriteAndSuggestedDishesAdapter(Context context, List<Dish> listView){
        this.listView = listView;
        mInflater = LayoutInflater.from(context);
    }

    public  void setActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listView.size();
    }

    @Override
    public Object getItem(int position) {
        return listView.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.favorite_dishes_layout, parent, false);


        TextView name = layoutItem.findViewById(R.id.name1);
        ImageView image = layoutItem.findViewById(R.id.image1);


        name.setText(listView.get(position).getName());
        image.setImageResource(listView.get(position).getImage());

        final Dish d = listView.get(position);
        layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dishes.setCurrentDish(d);
                ((MainActivity) activity).openFragment(new HomeFragment());
            }
        });

        return layoutItem;
    }

    public void addListener(IListenItem itemToListen) {
        listViewListen = itemToListen;
    }
}
