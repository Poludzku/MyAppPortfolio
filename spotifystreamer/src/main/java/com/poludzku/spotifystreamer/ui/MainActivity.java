package com.poludzku.spotifystreamer.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.ui.dashboard.DashboardFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.dashboard, DashboardFragment.getInstance(), DashboardFragment.TAG);
        ft.addToBackStack(DashboardFragment.TAG);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
