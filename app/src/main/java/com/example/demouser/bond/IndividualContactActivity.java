package com.example.demouser.bond;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class IndividualContactActivity extends AppCompatActivity {
    public static final String NAME_TEXT = "com.example.demouser.bond.NAME_TEXT";
    public static final String EMAIL_TEXT = "com.example.demouser.bond.EMAIL_TEXT";
    public static final String PHONE_TEXT = "com.example.demouser.bond.PHONE_TEXT";
    public static final String DATE_TEXT = "com.example.demouser.bond.DATE_TEXT";
    public static final String IMAGE_TEXT = "com.example.demouser.bond.IMAGE_TEXT";
    public static final String NOTE_TEXT = "com.example.demouser.bond.NOTE_TEXT";
    protected String imageSrc = "";
    private Button nextTime;
    private Button nextDate;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_contact);
        handleSaveButton();

        ImageView picture = (ImageView) findViewById(R.id.picture);
        picture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Intent to open Gallery for image selection
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        // Use the current time as the default values for the next time button
        nextTime = findViewById(R.id.nextTime);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String am_pm = "";
        if (hour < 12) {
            am_pm = "AM";
        } else {
            hour = hour - 12;
            am_pm = "PM";
        }
        String minText = "";
        if (minute < 10) {
            minText = "0" + minute;
        } else {
            minText = "" + minute;
        }
        nextTime.setText("" + hour + " : " + minText + " " + am_pm);
        handleNextTimeButton();

        // Use the current date as the default values for the next date button
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        nextDate = findViewById(R.id.nextDate);
        DateFormatSymbols symbols  = new DateFormatSymbols();
        String monthText = symbols.getMonths()[month];
        nextDate.setText("" + monthText + " " + day + ", " + year);
        handleNextDateButton();
    }

    /**
     * Method to determine events when clicking Save button
     */
    public void handleSaveButton() {
        // Find the save button
        FloatingActionButton saveButton = findViewById(R.id.saveButton);

        // Add click listener and determine events after click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from name, email, phone text fields, save into variables
                EditText nameText = (EditText) findViewById(R.id.name);
                String name = nameText.getText().toString();

                EditText emailText = (EditText) findViewById(R.id.email);
                String email = emailText.getText().toString();

                EditText phoneText = (EditText) findViewById(R.id.phone);
                String phone = phoneText.getText().toString();

                EditText noteText = (EditText) findViewById(R.id.note);
                String note = noteText.getText().toString();

                // Create a new intent to hold each data
                Intent intent = new Intent();

                // Put each of the data along with a "key" name that will be used to get each data
                intent.putExtra(NAME_TEXT, name);
                intent.putExtra(EMAIL_TEXT, email);
                intent.putExtra(PHONE_TEXT, phone);
                intent.putExtra(DATE_TEXT, 2212);
                intent.putExtra(NOTE_TEXT, note);

                //if user added a new image then save the source of that image
                if (!imageSrc.equals("")) {
                    intent.putExtra(IMAGE_TEXT, imageSrc);
                }

                // Set the result to be the intent just created
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * Method to choose picture for image view
     *
     * @param requestCode an integer originally supplied to startActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode  an integer returned by the child activity through its setResult()
     * @param data        an Intent, which can return result data to the caller (various data can be attached to Intent "extras")
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.picture);
            imageView.setImageURI(selectedImage);
            imageSrc = selectedImage.toString();
        }

    }

    /**
     * Method invoked when user clicks nextDate button
     * Create a new date picker dialog
     */
    public void handleNextDateButton() {
        nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "Date picker");
                Log.d("My activity", "Called new Fragment");
            }
        });
    }

    /**
     * Method invoked when user clicks nextTime button
     * Create a new time picker dialog
     */
    public void handleNextTimeButton() {
        nextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "Time picker");
            }
        });
    }
}