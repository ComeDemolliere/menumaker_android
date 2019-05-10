package com.ihm.project.menumaker.fragments.fridge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Ingredients;

import java.util.ArrayList;
import java.util.List;


public class FridgeProvisions extends Fragment implements AbsListView.MultiChoiceModeListener {

    ListView provisions;
    List provisons2=Ingredients.getProvisions();
    IngredientsListAdapter fridgeAdapter;

    public static FridgeProvisions newInstance() {
        return new FridgeProvisions();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fridge_provisions, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        fridgeAdapter = new IngredientsListAdapter(getContext(), Ingredients.getProvisions());

        provisions = layoutItem.findViewById(R.id.provisions);

        Button remove= (Button) layoutItem.findViewById(R.id.remove);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSelected();
            }
        });

        provisions.setAdapter(fridgeAdapter);


        return view;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        mode.setTitle(provisions.getCheckedItemCount() + " selected ingredients");
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                removeSelected();
                mode.finish();
                break;
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    private void removeSelected()   {
        SparseBooleanArray checkeditems = provisions.getCheckedItemPositions();
        ArrayList itemsToDelete = new ArrayList<>();

        System.out.println(checkeditems);

        for(int i = provisions.getCount() - 1; i >= 0; i--) {
            if(checkeditems.get(i)) {
                //adapter.remove(data.get(i));
                itemsToDelete.add(fridgeAdapter.getItem(i));
            }
        }

        //Boucle de suppression
        for(int i = 0; i < itemsToDelete.size(); i++) {
            itemsToDelete.remove(itemsToDelete.get(i));
        }

        fridgeAdapter.notifyDataSetChanged();
        checkeditems.clear();
    }
}