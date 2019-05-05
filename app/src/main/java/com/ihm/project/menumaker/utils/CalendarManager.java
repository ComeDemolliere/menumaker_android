package com.ihm.project.menumaker.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.ihm.project.menumaker.Samples.Dish;

public class CalendarManager {
    private Activity activity;
    private ContentResolver cr;
    private String id;

    public CalendarManager(Activity activity){
        this.activity = activity;
        this.cr = activity.getContentResolver();
        this.id = null;
    }

    public void init(){
        String projection[] = {"_id", "calendar_displayName"};
        Uri calendars;
        calendars = Uri.parse("content://com.android.calendar/calendars");

        Cursor managedCursor = cr.query(calendars, projection, null, null, null);

        if (managedCursor.moveToFirst()){
            String calName;
            int nameCol = managedCursor.getColumnIndex(projection[1]);
            int idCol = managedCursor.getColumnIndex(projection[0]);
            do {
                calName = managedCursor.getString(nameCol);
                this.id = managedCursor.getString(idCol);
                System.out.println(calName + " " + id);
            } while(managedCursor.moveToNext());
            managedCursor.close();
        }
    }

    public void insert(Dish d){
        if(this.id != null){
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, d.getDate().getTime());
            values.put(CalendarContract.Events.DTEND, d.getDate().getTime());
            values.put(CalendarContract.Events.TITLE, d.getName());
            values.put(CalendarContract.Events.DESCRIPTION, d.getReceipe());
            values.put(CalendarContract.Events.CALENDAR_ID, id);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, "French/Paris");

            //insert event
            cr.insert(CalendarContract.Events.CONTENT_URI, values);
        }
    }
}
