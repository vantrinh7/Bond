package com.example.demouser.bond;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

import java.util.ArrayList;

/**
 * Created by demouser on 1/17/18.
 * Adapter for contact list display.
 */

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context; //store what the list view is on
    private final ArrayList<Uri> imageIDarray;
    private final ArrayList<String> nameArray;

    public CustomListAdapter(Activity context, ArrayList<String> nameArrayParam, ArrayList<Uri> imageIDArrayParam){

        super(context,R.layout.listview_row, nameArrayParam);
        this.context = context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;

    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1ID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray.get(position));
        imageView.setImageURI(imageIDarray.get(position));
        return rowView;

    };


}
