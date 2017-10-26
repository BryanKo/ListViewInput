package com.bryankoproject.cmps121asg1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<PhotoList> photoList;
    FloatingActionButton fabCreate, fabClose, fabView;
    ListView listView;
    PhotoList photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDB = new DatabaseHelper(this);
        photoList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();

        fabCreate = (FloatingActionButton) findViewById(R.id.fabCreate);
        fabCreate.setImageResource(R.drawable.ic_add_white_24dp);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PhotoInfoInput.class);
                startActivity(i);
            }
        });

        fabClose = (FloatingActionButton) findViewById(R.id.fabClose);
        fabClose.setImageResource(R.drawable.ic_close_white_24dp);
        fabClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fabView = (FloatingActionButton) findViewById(R.id.fabView);
        fabView.setImageResource(R.drawable.ic_pageview_white_24dp);
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        if(numRows == 0) {
            Toast.makeText(MainActivity.this, "Database Empty",Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()) {
                int i = 0;
                photo = new PhotoList(data.getString(1), data.getString(2), data.getString(3));
                photoList .add(i,photo);
            }
            Three_ListAdapter adapter = new Three_ListAdapter(this, R.layout.photoinfo_layout, photoList);
            listView = (ListView) findViewById(R.id.lvPhoto);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
