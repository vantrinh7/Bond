package com.example.demouser.bond;

/**
 * Created by demouser on 1/19/18.
 */

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A class that defines properties of time picker fragment and events when user sets time
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    /**
     * Method is invoked when dialog is created
     *
     * @param saveInstanceState the instance state
     * @return a time picker dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Return a new instance of TimePickerDialog
        // 1st parameter - the context, 2nd - style, 3rd - onTimeSetListener, 4th - hour, 5th - minute, 6th - boolean is24HourFormat
        return new TimePickerDialog(getActivity(), TimePickerDialog.THEME_HOLO_LIGHT, this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }

    /**
     * Method invoked when user hit OK button to set time
     *
     * @param timePicker the current time picker
     * @param hour       the hour
     * @param minute     the minute
     */
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        // Determine AM or PM
        String am_pm = "";
        if (hour < 12) {
            am_pm = "AM";
        } else {
            hour = hour - 12;
            am_pm = "PM";
        }

        // Add a zero at the beginning of minute if it's smaller than 10
        String minText = "";
        if (minute < 10) {
            minText = "0" + minute;
        } else {
            minText = "" + minute;
        }
        // Change text of the button and set color to green
        ((Button) getActivity().findViewById(R.id.nextTime)).setText("" + hour + ":" + minText + " " + am_pm);
        ((Button) getActivity().findViewById(R.id.nextTime)).setTextColor(Color.rgb(30, 175, 20));
    }
}
