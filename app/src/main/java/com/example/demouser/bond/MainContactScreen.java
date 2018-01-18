package com.example.demouser.bond;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainContactScreen extends AppCompatActivity {

    public static final int REQUEST_CODE_INDIVIDUAL_CONTACT_ACTIVITY = 0;

    private String m_Text = "";
    private ArrayList<String> values = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                initiateAddButton();
            }
        });

        Button calendarButton = findViewById(R.id.button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initiateCalendarButton();

            }
        });
        ListView contactList = (ListView) findViewById(R.id.contactList);

        values.add("Mary Lyon");

        // Define a new Adapter: First parameter - Context, Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written, Forth - the Array of data
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        contactList.setAdapter(adapter);
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

                // Get the name, email, phone data from the IndividualContactActivity class
                String name = data.getStringExtra(IndividualContactActivity.NAME_TEXT);
                String email = data.getStringExtra(IndividualContactActivity.EMAIL_TEXT);
                String phone = data.getStringExtra(IndividualContactActivity.PHONE_TEXT);

                // Change the ArrayAdapter and display
                values.add(name + "  " + email + "  " + phone);
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
