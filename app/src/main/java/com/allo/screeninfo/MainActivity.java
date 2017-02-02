package com.allo.screeninfo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.allo.screeninfo.density.DensityFragment;
import com.allo.screeninfo.orientation.OrientationFragment;
import com.allo.screeninfo.size.SizeFragment;
import com.allo.screeninfo.text.TextFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity implements DensityFragment.OnFragmentInteractionListener,
        TextFragment.OnFragmentInteractionListener,
        OrientationFragment.OnFragmentInteractionListener,
        SizeFragment.OnFragmentInteractionListener {

    private static final String TAG_LOG = MainActivity.class.getCanonicalName();

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    @State
    int mSelectedMenuOptionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG_LOG, "onCreate");

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeUI();

        Icepick.restoreInstanceState(this, savedInstanceState);

        if (savedInstanceState == null) {
            selectDrawerItem(mNavigationView.getMenu().findItem(R.id.density_fragment));
        } else {
            mNavigationView.getMenu().findItem(mSelectedMenuOptionId).setChecked(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG_LOG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG_LOG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG_LOG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG_LOG, "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG_LOG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG_LOG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initializeUI() {
        setSupportActionBar(mToolbar);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.density_fragment:
                fragment = DensityFragment.newInstance();
                break;
            case R.id.text_fragment:
                fragment = TextFragment.newInstance();
                break;
            case R.id.orientation_fragment:
                fragment = OrientationFragment.newInstance();
                break;
            case R.id.size_fragment:
                fragment = SizeFragment.newInstance();
                break;
            default:
                fragment = DensityFragment.newInstance();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Set action bar title
        setTitle(menuItem.getTitle());

        // Close the navigation drawer
        mDrawerLayout.closeDrawers();

        // Save selected option
        mSelectedMenuOptionId = menuItem.getItemId();
    }

}
