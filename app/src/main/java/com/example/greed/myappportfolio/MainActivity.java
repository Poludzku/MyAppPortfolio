package com.example.greed.myappportfolio;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.greed.myappportfolio.model.ButtonAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final Map<Integer, ButtonAction> buttonMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonMap.put(R.id.spotfi_button, ButtonAction.createInstance("launch spotify", null, getPackageManager()));
        buttonMap.put(R.id.scores_button, ButtonAction.createInstance("launch scores app", null, getPackageManager()));
        buttonMap.put(R.id.library_button, ButtonAction.createInstance("launch library app", null, getPackageManager()));
        buttonMap.put(R.id.bigger_button, ButtonAction.createInstance("launch BIGGER app", null, getPackageManager()));
        buttonMap.put(R.id.xyz_button, ButtonAction.createInstance("launch xyz Reader app", null, getPackageManager()));
        buttonMap.put(R.id.capstone_button, ButtonAction.createInstance("launch capstone", null, getPackageManager()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    @Override
    public void onClick(View v) {
        // Perform action on click
        int id = v.getId();
        ButtonAction buttonAction = buttonMap.get(id);
        if (buttonAction.getIntent() == null) {
            Context context = MainActivity.this;

            Toast.makeText(context, buttonAction.getMessage(), Toast.LENGTH_SHORT).show();

        } else {
            startActivity(buttonAction.getIntent());
        }


    }
}
