package com.ihm.project.menumaker.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihm.project.menumaker.MainActivity;
import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.fragments.IListenItem;
import com.ihm.project.menumaker.models.GuestModel;
import com.tomash.androidcontacts.contactgetter.entity.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactsAdapter extends BaseAdapter implements Filterable {

    private List<ContactData> contactsList;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Filter filter;
    private List<ContactData> mOriginalValues;
    private Activity activity;

    public ContactsAdapter(Context context, List<ContactData> list, Activity activity){
            this.contactsList = list;
            mInflater = LayoutInflater.from(context);
            this.activity = activity;
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
        layoutItem.setOnClickListener(view -> {
            Log.d("bo", "boosalsa");
            int pos = (Integer) view.getTag();
            MainActivity mainActivity = (MainActivity) activity;
            mainActivity.openCreateGuestWithContactSelected(view, contactsList.get(pos).getCompositeName());
        });


        return layoutItem;
    }
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,FilterResults results)
                {

                    contactsList = (List<ContactData>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    List<ContactData> filtredArray = new ArrayList<ContactData>();


                    if (mOriginalValues == null)
                    {
                        mOriginalValues = new ArrayList<ContactData>(contactsList); // saves the original data in mOriginalValues
                    }

                    /********
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns filtredArray(Filtered)
                     *
                     ********/
                    if (constraint == null || constraint.length() == 0)
                    {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    }
                    else
                    {
                        final CharSequence prefix = constraint.toString().toLowerCase();
                        filtredArray = contactsList.stream().filter(elem -> elem.getCompositeName().toLowerCase().startsWith(prefix.toString())).collect(Collectors.toList());
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
