package com.example.demouser.bond;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainContactScreen extends AppCompatActivity {

    public static final int REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY = 0;

    private String m_Text = "";
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Uri> imageArray = new ArrayList<> ();
    private HashMap<String, IndividualContact> contacts = new HashMap<> ();
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
    }

    /**
     * Method to set up the contact list view
     */
    public void setupContactList () {
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


    public void initiateCalendarButton(){
        Intent intent = new Intent(this, CalendarMainActivity.class);

        startActivity(intent);
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
     * @param requestCode supplied by startActivityForResult(), it identifies who this result came from
     * @param resultCode the integer result code returned by the child activity through its setResult()
     * @param data an intent that holds the data to be returned
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
                    System.out.println("Uri is: " + imageUri);
                    imageArray.add(imageUri);
                }
                else {
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
