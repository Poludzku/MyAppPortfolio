package com.poludzku.spotifystreamer.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.dashboard.DashboardFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getFragmentManager().findFragmentByTag(DashboardFragment.TAG);
        if (fragment == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.dashboard, DashboardFragment.getInstance(), DashboardFragment.TAG);
            ft.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0 || getResources().getBoolean(R.bool.isTablet)) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
