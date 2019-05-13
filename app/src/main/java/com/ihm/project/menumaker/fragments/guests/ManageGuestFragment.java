package com.ihm.project.menumaker.fragments.guests;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ihm.project.menumaker.MainActivity;
import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.adapters.GuestAdapter;
import com.ihm.project.menumaker.models.GuestModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ManageGuestFragment extends Fragment {

    private ListView guestListView;
    private GuestAdapter guestAdapter;

    private List<GuestModel.Guest> guestList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_guest,
                container, false);
         this.guestListView = view.findViewById(R.id.guestsListView);
         this.guestList = GuestModel.getGuests();
         this.guestAdapter = new GuestAdapter(getContext(), this.guestList);


        guestListView.setAdapter(guestAdapter);

        EditText editText = (EditText) view.findViewById(R.id.searchGuest);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guestAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button button = view.findViewById(R.id.validateButton);
        button.setOnClickListener(buttonView -> {
            MainActivity activity = (MainActivity) getContext();
            activity.setCurrentSelectedGuest(guestAdapter.getSelectedGuestList());
            activity.openDishesFragment();
        });
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        this.guestList = GuestModel.getGuests();
        this.guestAdapter.setGuests(this.guestList);
        this.guestAdapter.reset();
        guestAdapter.notifyDataSetChanged();
    }
}
