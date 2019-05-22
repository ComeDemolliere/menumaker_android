package com.ihm.project.menumaker.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.fragments.home.HomeFragment;
import com.ihm.project.menumaker.models.Dishes;
import com.ihm.project.menumaker.utils.CalendarManager;

import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ValidateDishFragment extends Fragment {

    private TextView dateText;
    private CalendarView calendar;
    boolean check = true;
    CalendarManager calendarManager;

    public static ValidateDishFragment newInstance() {
        return new ValidateDishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.validate_dish_fragment, container, false);

        CalendarManager calendarManager = new CalendarManager(getActivity());

        final Dish dish = Dishes.getCurrentDish();
        calendar = view.findViewById(R.id.calendarView);
        dateText = view.findViewById(R.id.date);
        Date currentDate = new Date(calendar.getDate());
        dish.setDate(currentDate);
        dateText.setText(currentDate.getDate() + "/" + (currentDate.getMonth() + 1) + "/" + (currentDate.getYear() + 1900));
        Switch swi = view.findViewById(R.id.switch2);

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                check = isChecked;
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dish.setDate(new Date(year - 1900, month, dayOfMonth));
                dateText.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        Button button = view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check) addEventToCalendar(calendarManager);
                else {
                    consumeDish();
                }
                Intent intent = new Intent();
                intent.setAction("registerReceiver");
                intent.putExtra("ingToBuyListService", 3);
                intent.putExtra("ToastContent", "Succefuly Added");
                getActivity().sendBroadcast(intent);
            }
        });

        return view;
    }

    private boolean addEventToCalendar(CalendarManager calendarManager){
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, 30);
        }
        else {
            calendarManager.init();
            calendarManager.insert(Dishes.getCurrentDish());
            consumeDish();
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 30:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    consumeDish();
                }
                break;
            default:break;

        }
    }

    private void consumeDish(){
        Dishes.eatDish();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();
    }
}
