package com.example.demouser.bond;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Page for displaying information of a single contact
 * Author: Olive.
 */
public class IndividualContactPage extends AppCompatActivity {

    protected static final int RESULT_EDIT = 3;
    public static final String NAME_TEXT = "com.example.demouser.bond.NAME_TEXT";
    public static final String EMAIL_TEXT = "com.example.demouser.bond.EMAIL_TEXT";
    public static final String PHONE_TEXT = "com.example.demouser.bond.PHONE_TEXT";
    public static final String DATE_TEXT = "com.example.demouser.bond.DATE_TEXT";
    public static final String IMAGE_TEXT = "com.example.demouser.bond.IMAGE_TEXT";
    public static final String NOTE_TEXT = "com.example.demouser.bond.NOTE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_contact_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //grab all the view
        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);
        TextView lastContact = (TextView) findViewById(R.id.lastContact);
        TextView note = (TextView) findViewById(R.id.note);
        TextView nextContact = (TextView) findViewById(R.id.nextContact);
        ImageView profile = (ImageView) findViewById(R.id.profilePic);

        //set information according to the information in intent
        name.setText(getIntent().getStringExtra("name"));
        phone.setText(getIntent().getStringExtra("phone"));
        email.setText(getIntent().getStringExtra("email"));
        //lastContact.setText(getIntent().getStringExtra("lastContact"));
        lastContact.setText("");
        nextContact.setText(getIntent().getStringExtra("nextContact"));
        note.setText(getIntent().getStringExtra("note"));


        //set image source from image in intent
        Uri uri = Uri.parse(getIntent().getStringExtra("image"));
        profile.setImageURI(uri);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualContactPage.this, IndividualContactActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("phone", getIntent().getStringExtra("phone"));
                intent.putExtra("image", getIntent().getStringExtra("image"));
                intent.putExtra("lastContact", getIntent().getStringExtra("lastContact"));
                intent.putExtra("nextContact", getIntent().getStringExtra("nextContact"));
                //System.out.println("Next contact is: " + getIntent().getStringExtra("nextContact"));
                intent.putExtra("note", getIntent().getStringExtra("note"));
                intent.putExtra("ID", "IndividualContactPage");
                startActivity(intent);
            }
        });
    }

    /**
     * Method to navigate back to previous home screen
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
