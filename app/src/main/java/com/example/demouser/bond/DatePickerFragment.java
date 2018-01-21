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

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * A class that defines properties of time picker fragment and events when user sets time
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private boolean isDateSet = false;
    private int theYear;
    private int theMonth;
    private int theDate;
    private int theDayOfWeek;

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
        theYear = c.get(Calendar.YEAR);
        theMonth = c.get(Calendar.MONTH);
        theDate = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, theYear, theMonth, theDate);
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
        theYear = year;
        theMonth = month;
        theDate = day;
        theDayOfWeek = datePicker.getFirstDayOfWeek();

        // Get month in word form
        DateFormatSymbols symbols  = new DateFormatSymbols();
        String monthText = symbols.getMonths()[month];

        String dayText = "";
        switch (theDayOfWeek) {
            case 1:
                dayText = "Sunday";
                break;
            case 2:
                dayText = "Monday";
                break;
            case 3:
                dayText = "Tuesday";
                break;
            case 4:
                dayText = "Wednesday";
                break;
            case 5:
                dayText = "Thursday";
                break;
            case 6:
                dayText = "Friday";
                break;
            case 7:
                dayText = "Saturday";
                break;
        }

        // Set button to show the date chosen and set color to green
        ((Button) getActivity().findViewById(R.id.nextDate)).setText(dayText + ", " + monthText + " " + day + ", " + year);
        ((Button) getActivity().findViewById(R.id.nextDate)).setTextColor(Color.rgb(30, 175, 20));

        // Jump to time picker page
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "Time picker");

        isDateSet = true;
    }

    public boolean isDateSet() {
        return isDateSet;
    }

    public int getYear() {
        return theYear;
    }

    public int getMonth() {
        return theMonth;
    }

    public int getDate() {
        return theDate;
    }
}
