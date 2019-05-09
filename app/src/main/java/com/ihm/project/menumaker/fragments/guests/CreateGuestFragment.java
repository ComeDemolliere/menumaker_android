package com.ihm.project.menumaker.fragments.guests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ihm.project.menumaker.R;

public class CreateGuestFragment extends Fragment {

    private String name = "";

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_guest_layout,
                container, false);

        EditText editText = v.findViewById(R.id.nameGuest);
        editText.setText(this.name);
        return v;
    }
}
