package com.example.demouser.bond;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Page for displaying information of a single contact
 */
public class IndividualContactPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_contact_page);

        //grab all the view
        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);
        TextView lastContact = (TextView) findViewById(R.id.lastContact);
        TextView note = (TextView) findViewById(R.id.note);
        ImageView profile = (ImageView) findViewById(R.id.profilePic);

        //set information according to the information in intent
        name.setText(getIntent().getStringExtra("name"));
        phone.setText(getIntent().getStringExtra("phone"));
        email.setText(getIntent().getStringExtra("email"));
        lastContact.setText(getIntent().getStringExtra("lastContact"));
        note.setText(getIntent().getStringExtra("note"));

        //set image source from image in intent
        Uri uri = Uri.parse(getIntent().getStringExtra("image"));
        profile.setImageURI(uri);
    }


}
