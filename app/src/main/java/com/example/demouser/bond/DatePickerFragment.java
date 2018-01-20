package com.example.demouser.bond;

/**
 * Created by demouser on 1/19/18.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;


/**
 * An inner class that defines properties of time picker fragment and events when user sets time
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public Date date;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


    /**
     * Method is invoked when dialog is created
     *
     * @param saveInstanceState the instance state
     * @return a date picker dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        Log.d("My activity", "Create dialog");
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    /**
     * Method invoked when user hit OK button to set date
     *
     * @param datePicker the current date picker
     * @param year       the year
     * @param month      the month
     * @param day        the day
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Get month in word form
        DateFormatSymbols symbols  = new DateFormatSymbols();
        String monthText = symbols.getMonths()[month];

        // Set button to show the date chosen and set color to green
        ((Button) getActivity().findViewById(R.id.nextDate)).setText(monthText + " " + day + ", " + year);
        ((Button) getActivity().findViewById(R.id.nextDate)).setTextColor(Color.rgb(30, 175, 20));

        // Create a new date object
        date = new GregorianCalendar(year, month, day).getTime();

        System.out.println("Date obtained! Parsing: " + dateFormat.format(date));
        getDate();

    }

    public Date getDate() {
       // System.out.println("Parsing: " + dateFormat.format(date));
        return date;
    }
}
