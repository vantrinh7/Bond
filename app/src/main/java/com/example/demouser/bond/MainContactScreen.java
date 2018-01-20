package com.example.demouser.bond;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainContactScreen extends AppCompatActivity {

    public static final int REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY = 0;

    private String m_Text = "";
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Uri> imageArray = new ArrayList<>();
    private HashMap<String, IndividualContact> contacts = new HashMap<>();
    private CustomListAdapter adapter;
    private ListView contactList;
    private final Uri original = Uri.parse("android.resource://com.example.demouser.bond/drawable/octopus");


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

        handleTestNotiButton();
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

                //getting the contact information corresponding to the name
                IndividualContact target = contacts.get(message);
                intent.putExtra("name", target.name);
                intent.putExtra("email", target.email);
                intent.putExtra("phone", target.phone);
                intent.putExtra("image", target.image.toString());
                intent.putExtra("lastContact", target.lastContact);
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

        // Use startActivity() to simply start a new screen, use startActivityForResult() to get some results back.
        // Must implement onActivityResult() method below to denote what happens when result comes back
        // First parameter - the new intent, second parameter - the code that identifies where the result comes from
        startActivityForResult(intent, REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY);
    }

    /**
     * Method that determines what happens when result comes back from the child activity
     *
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
                Uri imageUri = original;
                if (data.hasExtra(IndividualContactActivity.IMAGE_TEXT)) {
                    String image = data.getStringExtra(IndividualContactActivity.IMAGE_TEXT);
                    imageUri = Uri.parse(image);
                    //System.out.println("Uri is: " + image)
                    imageArray.add(imageUri);
                } else {
                    imageArray.add(original);
                }
                String note = data.getStringExtra(IndividualContactActivity.NOTE_TEXT);

                //create new contact with given information
                IndividualContact contact1 = new IndividualContact(name, email, phone, "2212", note, imageUri);
                //add contact to contactList HashMap with key = name and value = the contact
                contacts.put(name, contact1);
                //update name in name array
                nameList.add(name);
                //notify adapter
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void handleTestNotiButton() {
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendNotifications();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method to send notifications when invoked
     */
    public void sendNotifications() throws IOException {
        // Create a notification manager that manages different channels
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // The id of the channel.
        String id = "my_channel_01";

        //Now build notification. For Android 8.0 (API level 26) and higher, a Notification channel is required.
        //But we don't need it for our app since its minimum API level is 21
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(this, id)
                        .setSmallIcon(R.drawable.octopus)
                        .setContentTitle("This notification title is from Bond")
                        .setContentText("Keep in touch");

        // When user clicks on the notification, come back to the program
        Intent resultIntent = new Intent(this, MainContactScreen.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of the app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainContactScreen.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        // Lead user to the program content and clear the notification when user clicks on it
        nBuilder.setContentIntent(resultPendingIntent);

        // Optional, but set the priority of the notification to be maximum when user sees it
        nBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        // Set large icon such as profile picture in the notification
        Bitmap largeIcon = MediaStore.Images.Media.getBitmap(this.getContentResolver(), original);
        nBuilder.setLargeIcon(largeIcon);

        // mNotificationId is a unique integer the app uses to identify the notification
        // Use this to identify which notification to submit to the manager
        int mNotificationId = 001;
        mNotificationManager.notify(mNotificationId, nBuilder.build());
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
