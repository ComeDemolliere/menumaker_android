package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.adapters.GuestAdapter;
import com.ihm.project.menumaker.models.GuestModel;

import java.util.ArrayList;

public class CreateGuestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_guest,
                container, false);
        ListView guests = view.findViewById(R.id.guestsListView);
        GuestModel.addGuest(new GuestModel.Guest("bo", new ArrayList<Dish>()));
        GuestAdapter guestAdapter = new GuestAdapter(getContext(), GuestModel.getGuests());
        guests.setAdapter(guestAdapter);
        return view;
    }


}
