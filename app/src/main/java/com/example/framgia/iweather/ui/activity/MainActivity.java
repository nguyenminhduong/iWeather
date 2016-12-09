package com.example.framgia.iweather.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgia.iweather.R;
import com.example.framgia.iweather.data.model.forecast.CurrentWeather;
import com.example.framgia.iweather.data.model.forecast.DataOfWeatherDate;
import com.example.framgia.iweather.data.model.forecast.Weather;
import com.example.framgia.iweather.data.model.geocode.AddressComponent;
import com.example.framgia.iweather.data.model.geocode.GeoCode;
import com.example.framgia.iweather.service.API;
import com.example.framgia.iweather.ui.adapter.WeatherAdapter;
import com.example.framgia.iweather.utils.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.framgia.iweather.service.ConvertUnit.convertWeather;
import static com.example.framgia.iweather.service.LocationUtils.askPermissionsAndShowMyLocation;
import static com.example.framgia.iweather.service.LocationUtils.getLocation;
import static com.example.framgia.iweather.service.LocationUtils.isGPSEnabled;
import static com.example.framgia.iweather.utils.Const.RequestCode.REQUEST_ACTION_SETTINGS;

public class MainActivity extends AppCompatActivity
    implements SwipeRefreshLayout.OnRefreshListener {
    private static final String DATE_FORMAT = "EEEE";
    private static final int PERCEN_HUMIDITY = 100;
    private static final long DATE_CONVERT = 1000l;
    @BindView(R.id.text_city_name)
    TextView mTextViewCity;
    @BindView(R.id.text_temp_current)
    TextView mTextViewTempCurrent;
    @BindView(R.id.text_summary_current)
    TextView mTextViewSummaryCurrent;
    @BindView(R.id.text_temp_real_feel)
    TextView mTextViewTempRealFeel;
    @BindView(R.id.text_humidity_current)
    TextView mTextViewHumidityCurrent;
    @BindView(R.id.text_temp_max_current)
    TextView mTextViewTempMaxCurrent;
    @BindView(R.id.text_temp_min_current)
    TextView mTextViewTempMinCurrent;
    @BindView(R.id.text_wind_current)
    TextView mTextViewWindCurrent;
    @BindView(R.id.text_wind_current_unit)
    TextView mTextViewWindCurrentUnit;
    @BindView(R.id.text_summary)
    TextView mTextViewSummary;
    @BindView(R.id.image_icon_weather_current)
    ImageView mImageViewIconCurrent;
    @BindView(R.id.recycler_view_weather_date)
    RecyclerView mRecyclerViewWeatherDate;
    @BindView(R.id.toolbar_activity_main)
    Toolbar mToolbarMain;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwifeToRefresh;
    private ProgressDialog mLoadingDialog;
    private WeatherAdapter mWeatherAdapter;
    private List<DataOfWeatherDate> mDateList = new ArrayList<>();
    private int mCountRequest;
    private String mTempUnit;
    private String mWindUnit;
    private String mLocation;
    private Weather mWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivityForResult(intent, REQUEST_ACTION_SETTINGS);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage(getString(R.string.loading_dialog_mess));
        getSetting();
        mToolbarMain.setTitle("");
        setSupportActionBar(mToolbarMain);
        mSwifeToRefresh.setOnRefreshListener(this);
        mWeatherAdapter = new WeatherAdapter(mDateList);
        mRecyclerViewWeatherDate.setHasFixedSize(true);
        mRecyclerViewWeatherDate.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerViewWeatherDate.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewWeatherDate.setAdapter(mWeatherAdapter);
    }

    @Override
    public void onRefresh() {
        getData();
        mSwifeToRefresh.setRefreshing(false);
    }

    private void getWeather(String latlng) {
        API.getWeather(latlng, new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (getApplicationContext() == null) return;
                checkCountRequest();
                if (response == null) {
                    Toast.makeText(getApplicationContext(), R.string.mess_tosk_weather_fail,
                        Toast.LENGTH_SHORT)
                        .show();
                    return;
                }
                mWeather = response.body();
                setWeather(convertWeather(mWeather, mTempUnit, mWindUnit));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                if (getApplicationContext() == null) return;
                checkCountRequest();
                Toast.makeText(getApplicationContext(), R.string.mess_tosk_weather_fail,
                    Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    private void getCityName(String latlng) {
        API.getCityName(latlng, new Callback<GeoCode>() {
            @Override
            public void onResponse(Call<GeoCode> call, Response<GeoCode> response) {
                if (getApplicationContext() == null) return;
                checkCountRequest();
                if (response == null) {
                    Toast.makeText(getApplicationContext(), R.string.mess_tosk_weather_fail, Toast
                        .LENGTH_SHORT)
                        .show();
                    return;
                }
                setCityName(response.body());
            }

            @Override
            public void onFailure(Call<GeoCode> call, Throwable t) {
                if (getApplicationContext() == null) return;
                checkCountRequest();
                Toast.makeText(getApplicationContext(), R.string.mess_tosk_weather_fail, Toast
                    .LENGTH_SHORT)
                    .show();
            }
        });
    }

    private void setCityName(GeoCode geoCodes) {
        if (geoCodes == null) return;
        if (geoCodes.getResults() == null
            || geoCodes.getResults().size() == 0) return;
        if (geoCodes.getResults().get(0)
            .getAddressComponents() == null || geoCodes.getResults().get(0)
            .getAddressComponents().size() == 0) return;
        List<AddressComponent> addressComponents =
            geoCodes.getResults().get(0).getAddressComponents();
        for (AddressComponent addressComponent : addressComponents) {
            List<String> types = addressComponent.getTypes();
            if (types == null || types.size() == 0) continue;
            if (types.get(0).equals(getString(R.string.key_short_name_city))) {
                mTextViewCity.setText(addressComponent.getShortName());
                break;
            }
        }
    }

    private void setWeather(Weather weather) {
        if (weather == null) return;
        if (weather.getDate().getData() == null || weather.getDate()
            .getData().size() == 0) return;
        if (weather.getCurrently() == null) return;
        List<DataOfWeatherDate> listTemp = weather.getDate().getData();
        CurrentWeather currentWeather = weather.getCurrently();
        mTextViewTempMaxCurrent.setText(Math.round(listTemp.get(1)
            .getTemperatureMax()) + "");
        mTextViewTempMinCurrent.setText(Math.round(listTemp.get(1)
            .getTemperatureMin()) + "");
        mTextViewTempCurrent.setText(Math.round(currentWeather.getTemperature()) + "");
        mTextViewSummaryCurrent.setText(currentWeather.getSummary());
        mTextViewHumidityCurrent
            .setText(Math.round(currentWeather.getHumidity() * PERCEN_HUMIDITY) + "");
        mTextViewWindCurrent.setText(Math.round(currentWeather.getWindSpeed()) + "");
        mTextViewSummary.setText(weather.getDate().getSummary());
        mTextViewTempRealFeel
            .setText(" " + Math.round(currentWeather.getApparentTemperature()));
        setWeatherIcon(mImageViewIconCurrent, currentWeather.getIcon());
        mDateList.clear();
        for (DataOfWeatherDate date : listTemp) {
            long unixTime = Long.parseLong(date.getTime());
            Date dateFromUnixTime = new Date(DATE_CONVERT * unixTime);
            date.setTime(new SimpleDateFormat(DATE_FORMAT).format
                (dateFromUnixTime));
            mDateList.add(date);
        }
        mWeatherAdapter.notifyDataSetChanged();
    }

    private void setWeatherIcon(ImageView imageView, String iconName) {
        switch (iconName) {
            case Const.IconWeather.CLEAR_NIGHT:
                imageView.setImageResource(R.drawable.ic_clear_night);
                break;
            case Const.IconWeather.CLOUDY:
                imageView.setImageResource(R.drawable.ic_cloudy);
                break;
            case Const.IconWeather.FOG:
                imageView.setImageResource(R.drawable.ic_fog);
                break;
            case Const.IconWeather.PARTLY_CLOUDY_DAY:
                imageView.setImageResource(R.drawable.ic_partly_cloudy_day);
                break;
            case Const.IconWeather.PARTLY_CLOUDY_NIGHT:
                imageView.setImageResource(R.drawable.ic_partly_cloudy_night);
                break;
            case Const.IconWeather.RAIN:
                imageView.setImageResource(R.drawable.ic_rain);
                break;
            case Const.IconWeather.SNOW:
                imageView.setImageResource(R.drawable.ic_snow);
                break;
            case Const.IconWeather.SLEET:
                imageView.setImageResource(R.drawable.ic_sleet);
                break;
            case Const.IconWeather.WIND:
                imageView.setImageResource(R.drawable.ic_wind);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_clear_day);
                break;
        }
    }

    private void getData() {
        if (isGPSEnabled(this)) {
            if (askPermissionsAndShowMyLocation(this)) {
                mLoadingDialog.show();
                mCountRequest = 2;
                mLocation = getLocation(this);
                getCityName(mLocation);
                getWeather(mLocation);
            }
        } else {
            showSettingsAlert();
        }
    }

    private void checkCountRequest() {
        mCountRequest--;
        if (mLoadingDialog == null) return;
        if (mCountRequest == 0 && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void getSetting() {
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this);
        mTempUnit = pre.getString(Const.UnitTemp.PREF_TEMP_UNIT, Const.UnitTemp.FAHRENHEIT);
        mWindUnit = pre.getString(Const.UnitWind.PREF_WIND_UNIT, Const.UnitWind.MILES_PER_HOURS);
        switch (mWindUnit) {
            case Const.UnitWind.KILOMETER_PER_HOUR:
                mTextViewWindCurrentUnit.setText(Const.UnitWind.KILOMETER_PER_HOUR);
                break;
            case Const.UnitWind.MILES_PER_HOURS:
                mTextViewWindCurrentUnit.setText(Const.UnitWind.MILES_PER_HOURS);
                break;
            case Const.UnitWind.METER_PER_SECOND:
                mTextViewWindCurrentUnit.setText(Const.UnitWind.METER_PER_SECOND);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Const.RequestCode.REQUEST_ID_ACCESS_COURSE_FINE_LOCATION:
                if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getData();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Const.RequestCode.REQUEST_ACTION_LOCATION_SOURCE_SETTINGS:
                    getData();
                    break;
                case REQUEST_ACTION_SETTINGS:
                    getSetting();
                    setWeather(convertWeather(mWeather, mTempUnit, mWindUnit));
                    break;
            }
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_message)
            .setPositiveButton(R.string.alert_positive_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings
                                .ACTION_LOCATION_SOURCE_SETTINGS),
                            Const.RequestCode.REQUEST_ACTION_LOCATION_SOURCE_SETTINGS);
                    }
                })
            .setNegativeButton(R.string.alert_negative_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
            .show();
    }
}
