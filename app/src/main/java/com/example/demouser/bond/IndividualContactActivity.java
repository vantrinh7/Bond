package com.example.demouser.bond;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class IndividualContactActivity extends AppCompatActivity {
    public static final String NAME_TEXT = "com.example.demouser.bond.NAME_TEXT";
    public static final String EMAIL_TEXT = "com.example.demouser.bond.EMAIL_TEXT";
    public static final String PHONE_TEXT = "com.example.demouser.bond.PHONE_TEXT";
    public static final String DATE_TEXT = "com.example.demouser.bond.DATE_TEXT";
    public static final String IMAGE_TEXT = "com.example.demouser.bond.IMAGE_TEXT";
    public static final String NOTE_TEXT = "com.example.demouser.bond.NOTE_TEXT";
    protected String imageSrc="";
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
    }

    /**
     * Method to determine events when clicking Save button
     */
    public void handleSaveButton (){
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
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

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

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle saveInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            // Return a new instance of TimePickerDialog
            // 1st parameter - the context, 2nd - onTimeSetListener, 3rd - hour, 4th - minute, 5th - boolean, 6 - is24HourFormat
            return new TimePickerDialog(getActivity(), this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            // When user hit set time, change the text view and save the time instance
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();


        }
    }
}