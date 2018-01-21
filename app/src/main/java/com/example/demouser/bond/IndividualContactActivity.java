package com.example.demouser.bond;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.text.DateFormatSymbols;
import java.util.Calendar;

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
    private EditText nameText;
    private EditText emailText;
    private EditText phoneText;
    private EditText noteText;
    private ImageView imageView;
    private String intentID;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_contact);
        handleSaveButton();

        //grab all the views
        nameText = (EditText) findViewById(R.id.name);
        emailText = (EditText) findViewById(R.id.email);
        phoneText = (EditText) findViewById(R.id.phone);
        noteText = (EditText) findViewById(R.id.note);
        imageView = (ImageView) findViewById(R.id.picture);
        nextDate = findViewById(R.id.nextDate);
        nextTime = findViewById(R.id.nextTime);

        Intent intent = this.getIntent();
        if (intent != null) {
            intentID = intent.getStringExtra("ID");
            if (intentID.equals("IndividualContactPage")) {
                getOldInformation(intent);
            }
        }

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

        setUpCurrentDate();
        handleNextDateButton();
    }

    /**
     * Set up current date
     */
    protected void setUpCurrentDate () {
        // Use the current time as the default values for the next time button
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
        if (intentID.equals("MainContactScreen")) {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DateFormatSymbols symbols  = new DateFormatSymbols();
            String monthText = symbols.getMonths()[month];
            nextDate.setText("" + monthText + " " + day + ", " + year);
        }
    }
    /**
     * Method to get information from the Individual Contact Page
     */
    protected void getOldInformation (Intent intent) {
        if (intent.getStringExtra("name") != null) {
            nameText.setText(intent.getStringExtra("name"));
        };
        if (intent.getStringExtra("email") != null) {
            emailText.setText(intent.getStringExtra("email"));
        }
        if (intent.getStringExtra("phone") != null) {
            phoneText.setText(intent.getStringExtra("phone"));
        }
        if (intent.getStringExtra("note") != null) {
            noteText.setText(intent.getStringExtra("note"));
        }
        if (intent.getStringExtra("nextContact") != null) {
            nextDate.setText(intent.getStringExtra("nextContact"));
        }
        if (intent.getStringExtra("image") != null) {
            String image = intent.getStringExtra("image");
            imageView.setImageURI(Uri.parse(image));
        }
    }
    /**
     * Method to determine events when clicking Save button
     */
    public void handleSaveButton() {
        // Find the save button
        FloatingActionButton saveButton = findViewById(R.id.button);

        // Add click listener and determine events after click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the previous screen is the main contact screen then pack information and finish the activity
                if (intentID.equals("MainContactScreen")) {
                    Intent intent = new Intent ();
                    packInformation(intent);
                    setResult(RESULT_OK, intent);
                    finish();

               }
                else if (intentID.equals("IndividualContactPage")) {
                    Intent newIntent = new Intent(IndividualContactActivity.this, MainContactScreen.class);
                    packInformation(newIntent);
                    startActivity(newIntent);
                }
            }
        });
    }

    /**
     * Method to package information about the contact
     */
    protected void packInformation (Intent intent) {
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        String note = noteText.getText().toString();
        String dateSet = nextDate.getText().toString();

        intent.putExtra(NAME_TEXT, name);
        intent.putExtra(EMAIL_TEXT, email);
        intent.putExtra(PHONE_TEXT, phone);
        intent.putExtra(DATE_TEXT, dateSet);
        intent.putExtra(NOTE_TEXT, note);

        if (!imageSrc.equals("")) {
            intent.putExtra(IMAGE_TEXT, imageSrc);
        }

        intent.putExtra("ID", "IndividualContactActivity");
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