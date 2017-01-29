package com.allo.screeninfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LOG = MainActivity.class.getCanonicalName();

    @BindView(R.id.rv_items)
    RecyclerView mRecyclerView;

    private List<ScreenInfo> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG_LOG, "onCreate");

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeUI();
        initializeData();
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
    }

    private void initializeUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        items = new ArrayList<>();
        KeyValueAdapter<ScreenInfo> adapter = new KeyValueAdapter<>(items);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        items.add(screenDensity());
        items.add(screenDensityTypeByCode());
        items.add(screenDensityTypeByResources());

        items.add(isPhone());
        items.add(is7Tablet());
        items.add(is10Tablet());
    }

    private ScreenInfo screenDensity() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density));
        screenInfo.setValue(String.valueOf(getResources().getDisplayMetrics().density));
        return screenInfo;
    }

    private ScreenInfo screenDensityTypeByCode() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density_type_code));

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                screenInfo.setValue(getString(R.string.screen_density_low));
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                screenInfo.setValue(getString(R.string.screen_density_medium));
                break;
            case DisplayMetrics.DENSITY_HIGH:
                screenInfo.setValue(getString(R.string.screen_density_high));
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xhigh));
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xxhigh));
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xxxhigh));
                break;
            case DisplayMetrics.DENSITY_260:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_280:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_300:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_340:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_360:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_400:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            case DisplayMetrics.DENSITY_420:
                screenInfo.setValue(getString(R.string.screen_density_tv));
                break;
            default:
                screenInfo.setValue(getString(R.string.screen_density_non_reliable));
                break;
        }

        return screenInfo;
    }

    private ScreenInfo screenDensityTypeByResources() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density_type_resources));
        screenInfo.setValue(getString(R.string.screen_density_value));
        return screenInfo;
    }

    private ScreenInfo isPhone() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_phone));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_phone)));
        return screenInfo;
    }

    private ScreenInfo is7Tablet() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_seven_inches_tablet));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_tablet_seven_inches)));
        return screenInfo;
    }

    private ScreenInfo is10Tablet() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_ten_inches_tablet));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_tablet_ten_inches)));
        return screenInfo;
    }

    private String getTextForBoolean(boolean item) {
        return getString(item ? R.string.screen_yes : R.string.screen_no);
    }
}
