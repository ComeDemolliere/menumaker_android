package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.project.menumaker.R;

public class FridgeFragment extends Fragment {

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View fridgeView = inflater.inflate(R.layout.home_fragment, container, false);


        return inflater.inflate(R.layout.fridge_fragment, container, false);
    }

}
