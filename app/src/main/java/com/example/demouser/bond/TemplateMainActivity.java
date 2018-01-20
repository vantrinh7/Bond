package com.example.demouser.bond;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TemplateMainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_template_main);

//        GridView gridview = (GridView) findViewById(R.id.gridView);
//
//        List<TemplateMainItemObjects> allItems = getAllItemObject();
//        TemplateMainAdapter templateMainAdapter = new TemplateMainAdapter(TemplateMainActivity.this, allItems);
//        gridview.setAdapter(templateMainAdapter);
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(TemplateMainActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
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

//    private List<TemplateMainItemObjects> getAllItemObject(){
//        TemplateMainItemObjects itemObject = null;
//        List<TemplateMainItemObjects> items = new ArrayList<>();
//        items.add(new TemplateMainItemObjects("Image One", "one"));
//        items.add(new TemplateMainItemObjects("Image Two", "two"));
//        items.add(new TemplateMainItemObjects("Image Three", "three"));
//        items.add(new TemplateMainItemObjects("Image Four", "four"));
//        items.add(new TemplateMainItemObjects("Image Five", "five"));
//        items.add(new TemplateMainItemObjects("Image Six", "six"));
//        items.add(new TemplateMainItemObjects("Image Seven", "seven"));
//        items.add(new TemplateMainItemObjects("Image Eight", "eight"));
//        items.add(new TemplateMainItemObjects("Image One", "one"));
//        items.add(new TemplateMainItemObjects("Image Two", "two"));
//        items.add(new TemplateMainItemObjects("Image Three", "three"));
//        items.add(new TemplateMainItemObjects("Image Four", "four"));
//        items.add(new TemplateMainItemObjects("Image Five", "five"));
//        items.add(new TemplateMainItemObjects("Image Six", "six"));
//        items.add(new TemplateMainItemObjects("Image Seven", "seven"));
//        items.add(new TemplateMainItemObjects("Image Eight", "eight"));
//        items.add(new TemplateMainItemObjects("Image One", "one"));
//        items.add(new TemplateMainItemObjects("Image Two", "two"));
//        items.add(new TemplateMainItemObjects("Image Three", "three"));
//        items.add(new TemplateMainItemObjects("Image Four", "four"));
//        items.add(new TemplateMainItemObjects("Image Five", "five"));
//        items.add(new TemplateMainItemObjects("Image Six", "six"));
//        items.add(new TemplateMainItemObjects("Image Seven", "seven"));
//        items.add(new TemplateMainItemObjects("Image Eight", "eight"));
//        return items;
//    }

}
