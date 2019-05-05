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
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.fragments.IListenItem2;

import java.util.List;

public class IngredientsListAdapter extends BaseAdapter {
    private List<Ingredient> listView;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private IListenItem2 listViewListen2;

    public IngredientsListAdapter(Context context, List<Ingredient> listView){
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
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.fridge_provisions, parent, false);


        //(2) : get widgets
        TextView name = layoutItem.findViewById(R.id.name_ingredient);
        ImageView image = layoutItem.findViewById(R.id.image_ingredient);


        //(3) : set values to widget
        name.setText(listView.get(position).getName());
        image.setImageResource(listView.get(position).getImage());

        //(4) : set tag as index
        layoutItem.setTag(position);

        //(5) : set listener on selected item
        layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                //TODO vérifier si unécouteur==null ou si maListeDeDiplome==null
                listViewListen2.onClickItem(listView.get(position));
            }
        });

        return layoutItem;
    }

    public void addListener(IListenItem2 itemToListen) {
        listViewListen2 = itemToListen;
    }
}
