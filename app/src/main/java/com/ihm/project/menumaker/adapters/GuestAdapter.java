package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.fragments.IListenItem;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.models.GuestModel;

import java.util.Date;
import java.util.List;

public class GuestAdapter extends BaseAdapter {
    private List<GuestModel.Guest> listView;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML

    public GuestAdapter(Context context, List<GuestModel.Guest> list){
        this.listView = list;
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
        ConstraintLayout layoutItem =
                (ConstraintLayout) mInflater.inflate(R.layout.guest_item_layout, parent, false);

        TextView name = layoutItem.findViewById(R.id.guestName);
        Button deleteButton = layoutItem.findViewById(R.id.deleteGuest);
        name.setText(listView.get(position).getName());
        deleteButton.setOnClickListener(event -> {
            this.listView.remove(position);
            this.notifyDataSetChanged();
        });
        return layoutItem;
    }
}
