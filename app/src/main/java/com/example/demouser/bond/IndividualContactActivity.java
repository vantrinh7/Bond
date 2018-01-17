package com.example.demouser.bond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IndividualContactActivity extends AppCompatActivity {
    public static final String NAME_TEXT = "com.example.demouser.bond.NAME_TEXT";
    public static final String EMAIL_TEXT = "com.example.demouser.bond.EMAIL_TEXT";
    public static final String PHONE_TEXT = "com.example.demouser.bond.PHONE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_contact);
        handleSaveButton();
    }

    /**
     * Method to determine events when clicking Save button
     */
    public void handleSaveButton (){
        // Find the save button
        Button saveButton = (Button) findViewById(R.id.button);

        // Add click listener and determine events after click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from name, email, phone text fields, save into variables
                EditText nameText = (EditText) findViewById(R.id.editText);
                String name = nameText.getText().toString();

                EditText emailText = (EditText) findViewById(R.id.editText2);
                String email = emailText.getText().toString();

                EditText phoneText = (EditText) findViewById(R.id.editText3);
                String phone = phoneText.getText().toString();

                // Create a new intent to hold each data
                Intent intent = new Intent();

                // Put each of the data along with a "key" name that will be used to get each data
                intent.putExtra(NAME_TEXT, name);
                intent.putExtra(EMAIL_TEXT, email);
                intent.putExtra(PHONE_TEXT, phone);

                // Set the result to be the intent just created
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
