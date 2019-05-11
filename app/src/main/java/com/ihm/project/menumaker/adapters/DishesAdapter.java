package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.File;
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
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.dishes_layout, parent, false);


        TextView name = layoutItem.findViewById(R.id.name);
        TextView date = layoutItem.findViewById(R.id.dateDish);
        ImageView image = layoutItem.findViewById(R.id.image);

        Dish dish = listView.get(position);

        name.setText(dish.getName());

        Bitmap bm;

        File imgFile = new  File(dish.getImage());

        if(imgFile.exists()){
            bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else{
            int ressource = dish.getImageWithContext(parent.getContext());
            bm = BitmapFactory.decodeResource(parent.getResources(), ressource);
        }
        image.setImageBitmap(bm);


        Date dishDate = listView.get(position).getDate();
        if(dishDate != null)
            date.setText(dishDate.getDate() + "/" + (dishDate.getMonth() + 1) + "/" + (dishDate.getYear() + 1900));

        layoutItem.setTag(position);

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
