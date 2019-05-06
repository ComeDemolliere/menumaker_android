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
import com.tomash.androidcontacts.contactgetter.entity.ContactData;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private List<ContactData> contactsList;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private IListenItem listViewListen;

    public ContactsAdapter(Context context, List<ContactData> list){
            this.contactsList = list;
            mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //(1) : inflate layout
        RelativeLayout layoutItem = (RelativeLayout) mInflater.inflate(R.layout.contact_list_item,
                parent, false);


        //(2) : get widgets
        TextView name = layoutItem.findViewById(R.id.name);

        String a = contactsList.get(position).getCompositeName()+ "";
        //(3) : set values to widget
        name.setText(a);

        //(4) : set tag as index
        layoutItem.setTag(position);

//        //(5) : set listener on selected item
//        layoutItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Integer position = (Integer)v.getTag();
//                //TODO vérifier si unécouteur==null ou si maListeDeDiplome==null
//                listViewListen.onClickItem(listView.get(position));
//            }
//        });

        return layoutItem;
    }

    public void addListener(IListenItem itemToListen) {
        listViewListen = itemToListen;
    }
}
