package com.example.demouser.bond;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class TemplateMainActivity extends AppCompatActivity {

    protected GridView gridView;
    protected TemplateGridAdapter gridAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_template_main);

            gridView = (GridView) findViewById(R.id.gridView);
            gridAdapter = new TemplateGridAdapter(this, R.layout.activity_template_main_grid_items, getData());
            gridView.setAdapter(gridAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent (TemplateMainActivity.this, ItemListActivity.class);
                    startActivity(intent);
                }
            });
        }

    // Prepare some dummy data for gridview
    private ArrayList<TemplateMainItemObjects> getData() {
        final ArrayList<TemplateMainItemObjects> imageItems = new ArrayList<>();
        //TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
        //Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                //R.drawable.icon_resource);
        imageItems.add(new TemplateMainItemObjects("Job Recruiting"));
        imageItems.add(new TemplateMainItemObjects("Holiday Greetings"));
        imageItems.add(new TemplateMainItemObjects("Birthday/ Anniversaries"));
        imageItems.add(new TemplateMainItemObjects("Conversation Starters"));

        return imageItems;
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            //getMenuInflater().inflate(R.menu.menu_main, menu);
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
