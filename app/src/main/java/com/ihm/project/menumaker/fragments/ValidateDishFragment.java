package com.ihm.project.menumaker.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.models.Dishes;

import java.util.Date;

public class ValidateDishFragment extends Fragment {

    private TextView dateText;
    private CalendarView calendar;
    private ContentResolver myResolver;

    public static ValidateDishFragment newInstance() {
        return new ValidateDishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.validate_dish_fragment, container, false);

        final Dish dish = Dishes.getCurrentDish();
        calendar = view.findViewById(R.id.calendarView);
        dateText = view.findViewById(R.id.date);
        Date currentDate = new Date(calendar.getDate());
        dish.setDate(currentDate);
        System.out.println(dish.getDate());
        dateText.setText(currentDate.getDate() + "/" + currentDate.getMonth() + "/" + (currentDate.getYear() + 1900));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dish.setDate(new Date(year - 1900, month, dayOfMonth));
                dateText.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });
        return view;
    }
}
