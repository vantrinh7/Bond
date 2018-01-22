package com.example.demouser.bond;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainContactScreen extends AppCompatActivity {

    public static final int REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY = 0;

    private static ArrayList<String> nameList = new ArrayList<>();
    private static ArrayList<Uri> imageArray = new ArrayList<>();
    public static HashMap<String, IndividualContact> contacts = new HashMap<> ();
    public static HashMap<String, EventObjects> eventList = new HashMap<>();
    public static List<EventObjects> mEvents = new ArrayList<>();
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private SimpleDateFormat dayFormatter = new SimpleDateFormat("dd", Locale.ENGLISH);
    private CustomListAdapter adapter;
    private ListView contactList;
    private final Uri original = Uri.parse("android.resource://com.example.demouser.bond/drawable/octopus");
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    private static IndividualContact currentTarget;
    private static int currentPosition;


    /**
     * Method called on startup
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                initiateAddButton();
            }
        });
        //set up contact list view
        setupContactList();

        Button calendar = (Button) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainContactScreen.this, CalendarMainActivity.class);
                startActivity(intent);
            }
        });

        Button template = (Button) findViewById(R.id.template);
        template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateTemplateButton();
            }
        });

        handleTestFBButton();

        //call method to save edited information
        Intent intent = this.getIntent();
        if (intent != null) {
            if (intent.hasExtra("ID")) {
                saveEditedInformation(intent);
                }
            }
        }


    /**
     * Method to save edited information
     */
    protected void saveEditedInformation (Intent intent) {
        String oldContactName = currentTarget.name;
        String oldContactDate = currentTarget.nextContact;

        Date d = parseStringtoDate(oldContactDate);
        String contactName = intent.getStringExtra(IndividualContactActivity.NAME_TEXT);
        if (d != null) {
            if (eventList.containsKey(dayFormatter.format(d) + formatter.format(d))) {
                int i = mEvents.indexOf(eventList.get(dayFormatter.format(d) + formatter.format(d)));
                if (i != -1) {
                    mEvents.remove(i);
                }
                eventList.remove(dayFormatter.format(d) + formatter.format(d));
            }
        }
        currentTarget.setName(intent.getStringExtra(IndividualContactActivity.NAME_TEXT));
        currentTarget.setEmail(intent.getStringExtra(IndividualContactActivity.EMAIL_TEXT));
        currentTarget.setNoteContact(intent.getStringExtra(IndividualContactActivity.NOTE_TEXT));
        currentTarget.setPhone(intent.getStringExtra(IndividualContactActivity.PHONE_TEXT));
        currentTarget.setNextContact(intent.getStringExtra(IndividualContactActivity.DATE_TEXT));

        Date newDate = parseStringtoDate(intent.getStringExtra(IndividualContactActivity.DATE_TEXT));
        if (newDate != null) {
            EventObjects event = new EventObjects("Contact " + contactName, newDate);
            //add events to HashMap + ArrayList
            eventList.put(dayFormatter.format(newDate) + formatter.format(newDate), event);
            mEvents.add(event);
        }

        String image = intent.getStringExtra(IndividualContactActivity.IMAGE_TEXT);
        if (image != null) {
            if (!image.equals("")) {
                currentTarget.setImage(Uri.parse(image));
            }
        }
        nameList.set(currentPosition, contactName);
        //remove old contact name from list if they are different
        if (!contactName.equals(oldContactName)) {
            contacts.put(contactName, currentTarget);
            contacts.remove(oldContactName);
            imageArray.set(currentPosition, Uri.parse(image));
        }
    }

    /**
     * Method to set up the contact list view
     */
    public void setupContactList() {
        //create and add adapter to ListView to display contacts
        adapter = new CustomListAdapter(this, nameList, imageArray);
        contactList = (ListView) findViewById(R.id.contactList);
        contactList.setAdapter(adapter);

        //make item in list clickable
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainContactScreen.this, IndividualContactPage.class);
                //get contact from contact HashMap
                String message = nameList.get(position);
                currentPosition = position;

                //getting the contact information corresponding to the name
                IndividualContact target = contacts.get(message);
                currentTarget = target;
                intent.putExtra("name", target.name);
                intent.putExtra("email", target.email);
                intent.putExtra("phone", target.phone);
                intent.putExtra("image", target.image.toString());
                intent.putExtra("lastContact", target.lastContact);
                intent.putExtra("nextContact", target.nextContact);
                intent.putExtra("note", target.note);

                //pass information into activity
                startActivity(intent);
            }
        });
    }


    /**
     * Method to determine events when add button is clicked
     */
    public void initiateAddButton() {
        // Create a new intent to start a new activity. First parameter: context, usually this.
        // Second parameter: the class that denotes new activity
        Intent intent = new Intent(this, IndividualContactActivity.class);
        intent.putExtra("ID", "MainContactScreen");

        // Use startActivity() to simply start a new screen, use startActivityForResult() to get some results back.
        // Must implement onActivityResult() method below to denote what happens when result comes back
        // First parameter - the new intent, second parameter - the code that identifies where the result comes from
        startActivityForResult(intent, REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY);
    }

    public void initiateTemplateButton(){

        Intent intent = new Intent(this, TemplateMainActivity.class);

        // Use startActivity() to simply start a new screen, use startActivityForResult() to get some results back.
        // Must implement onActivityResult() method below to denote what happens when result comes back
        // First parameter - the new intent, second parameter - the code that identifies where the result comes from
        startActivity(intent);
    }

    /**
     * Method to parse a String into a Date object
     */
    protected Date parseStringtoDate (String src) {
        Date d = null;
        try {
            d = sdf.parse(src);

        } catch (ParseException ex) {
            System.out.println("parse failed");
        }
        return d;
    }

    /**
     * Method that determines what happens when result comes back from the child activity
     * @param requestCode supplied by startActivityForResult(), it identifies who this result came from
     * @param resultCode  the integer result code returned by the child activity through its setResult()
     * @param data        an intent that holds the data to be returned
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // If the request code is the code for IndividualContactActivity
        if (requestCode == REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY) {

            // If the result code matches
            if (resultCode == RESULT_OK) {

                // Get the name, email, phone data, last contact, note and image from the IndividualContactActivity class
                String name = data.getStringExtra(IndividualContactActivity.NAME_TEXT);
                String email = data.getStringExtra(IndividualContactActivity.EMAIL_TEXT);
                String phone = data.getStringExtra(IndividualContactActivity.PHONE_TEXT);
                String date = data.getStringExtra(IndividualContactActivity.DATE_TEXT);
                String note = data.getStringExtra(IndividualContactActivity.NOTE_TEXT);
                Uri imageUri = original;

                //Set profile photo
                if (data.hasExtra(IndividualContactActivity.IMAGE_TEXT)) {
                    String image = data.getStringExtra(IndividualContactActivity.IMAGE_TEXT);
                    imageUri = Uri.parse(image);
                    imageArray.add(imageUri);
                } else {
                    imageArray.add(original);
                }

                //create new contact with given information
                IndividualContact contact1 = new IndividualContact(name, email, phone, "2212", date, note, imageUri);
                //add contact to contactList HashMap with key = name and value = the contact
                contacts.put(name, contact1);


                //parse String date into Date object
                Date d = parseStringtoDate(date);
                if (d != null) {
                    EventObjects event = new EventObjects("Contact " + name, d);
                    //add events to HashMap + ArrayList
                    eventList.put(dayFormatter.format(d) + formatter.format(d), event);
                    mEvents.add(event);
                }
                //update name in name array
                nameList.add(name);
                //notify adapter
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void handleTestFBButton() {
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });
    }

    public void loginFacebook() {
        Intent newIntent = new Intent(this, FacebookLoginActivity.class);
        startActivity(newIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_contact_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
