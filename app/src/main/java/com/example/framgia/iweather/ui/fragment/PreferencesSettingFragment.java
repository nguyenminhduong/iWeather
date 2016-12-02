package com.example.framgia.iweather.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.framgia.iweather.R;

/**
 * Created by chaupham on 12/8/2016.
 */
public class PreferencesSettingFragment extends PreferenceFragment
    implements SharedPreferences.OnSharedPreferenceChangeListener {
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_user_settings);
        PreferenceManager.getDefaultSharedPreferences(getActivity())
            .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (getActivity() == null) return;
        getActivity().setResult(Activity.RESULT_OK);
    }
}
