package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.ihm.project.menumaker.R;

import java.util.Date;

public class ValidateDishFragment extends Fragment {

    private TextView dateText;
    private CalendarView calendar;

    public static ValidateDishFragment newInstance() {
        return new ValidateDishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.validate_dish_fragment, container, false);


        calendar = view.findViewById(R.id.calendarView);
        dateText = view.findViewById(R.id.date);
        Date currentDate = new Date(calendar.getDate());
        dateText.setText(currentDate.getDay() + "/" + currentDate.getMonth() + "/" + currentDate.getYear());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dateText.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });
        return view;
    }
}
