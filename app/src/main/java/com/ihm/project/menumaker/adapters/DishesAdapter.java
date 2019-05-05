package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.fragments.IListenItem;
import com.ihm.project.menumaker.Samples.Dish;

import java.util.Date;
import java.util.List;

public class DishesAdapter extends BaseAdapter {
    private List<Dish> listView;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private IListenItem listViewListen;

    public DishesAdapter(Context context, List<Dish> listView){
        this.listView = listView;
        mInflater = LayoutInflater.from(context);
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
        //(1) : inflate layout
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.dishes_layout, parent, false);


        //(2) : get widgets
        TextView name = layoutItem.findViewById(R.id.name);
        TextView date = layoutItem.findViewById(R.id.dateDish);
        ImageView image = layoutItem.findViewById(R.id.image);


        //(3) : set values to widget
        name.setText(listView.get(position).getName());
        image.setImageResource(listView.get(position).getImage());
        Date dishDate = listView.get(position).getDate();
        if(dishDate != null)
            date.setText(dishDate.getDate() + "/" + dishDate.getMonth() + "/" + (dishDate.getYear() + 1900));

        //(4) : set tag as index
        layoutItem.setTag(position);

        //(5) : set listener on selected item
        layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                //TODO vérifier si unécouteur==null ou si maListeDeDiplome==null
                listViewListen.onClickItem(listView.get(position));
            }
        });

        return layoutItem;
    }

    public void addListener(IListenItem itemToListen) {
        listViewListen = itemToListen;
    }
}
