package com.example.framgia.iweather.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.framgia.iweather.R;
import com.example.framgia.iweather.ui.fragment.PreferencesSettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chaupham on 12/5/2016.
 */
public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_activity_setting)
    Toolbar mToolbarSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mToolbarSetting.setTitle("");
        mToolbarSetting.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbarSetting);
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.frame_preferences, new
                PreferencesSettingFragment())
            .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
