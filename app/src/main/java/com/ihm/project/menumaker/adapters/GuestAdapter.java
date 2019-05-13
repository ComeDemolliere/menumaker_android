package com.ihm.project.menumaker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.models.GuestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestAdapter extends BaseAdapter implements Filterable {
    private List<GuestModel.Guest> guestsList;
    private List<GuestModel.Guest> selectedGuestList;
    private LayoutInflater mInflater; //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private List<GuestModel.Guest> mOriginalValues;
    private Filter filter;

    public GuestAdapter(Context context, List<GuestModel.Guest> list){
        this.guestsList = list;
        mInflater = LayoutInflater.from(context);
        this.selectedGuestList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return guestsList.size();
    }

    @Override
    public Object getItem(int position) {
        return guestsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<GuestModel.Guest> getSelectedGuestList() {
        return selectedGuestList;
    }

    public void reset() {
        this.selectedGuestList.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem =
                (ConstraintLayout) mInflater.inflate(R.layout.guest_item_layout, parent, false);

        TextView name = layoutItem.findViewById(R.id.guestName);
        Button deleteButton = layoutItem.findViewById(R.id.deleteGuest);
        name.setText(guestsList.get(position).getName());
        name.setTag(position);

        if (selectedGuestList.contains(guestsList.get(position)))
            layoutItem.setBackgroundColor(Color.parseColor("#5668EC"));

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuestModel.Guest current = guestsList.get((Integer) v.getTag());
                Log.d("bo-bo", "onItemClick: ");
                if (!selectedGuestList.contains(current)) {
                    v.setBackgroundColor(Color.parseColor("#5668EC"));
                    selectedGuestList.add(current);
                }
                else {
                    selectedGuestList.remove(current);
                    v.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        deleteButton.setOnClickListener(event -> {
            this.guestsList.remove(position);
            GuestModel.deleteGuestByIndex(position);
            this.notifyDataSetChanged();
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

                    guestsList = (List<GuestModel.Guest>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    List<GuestModel.Guest> filtredArray = new ArrayList<GuestModel.Guest>();

                    if (mOriginalValues == null)
                    {
                        mOriginalValues = new ArrayList<GuestModel.Guest>(guestsList); // saves the original data in mOriginalValues
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
                        filtredArray = mOriginalValues.stream().filter(elem -> elem.getName().startsWith(prefix.toString())).collect(Collectors.toList());
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

    public void setGuests(List<GuestModel.Guest> guests) {
        this.guestsList = guests;
    }
}
