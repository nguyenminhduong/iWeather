package com.example.framgia.iweather.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgia.iweather.R;
import com.example.framgia.iweather.data.model.forecast.Weather;
import com.example.framgia.iweather.data.model.geocode.GeoCode;
import com.example.framgia.iweather.service.API;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
    SwipeRefreshLayout.OnRefreshListener {
    private final String MESS_TOASK_REFRESHED = "Refeshed";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {
        mToolbarMain.setTitle("");
        setSupportActionBar(mToolbarMain);
        mSwifeToRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mSwifeToRefresh.setRefreshing(false);
        Toast.makeText(this, MESS_TOASK_REFRESHED, Toast.LENGTH_LONG).show();
    }

    private void getWeather(String latlng) {
        API.getWeather(latlng, new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
            }
        });
    }

    private void getCityName(String latlng) {
        API.getCityName(latlng, new Callback<GeoCode>() {
            @Override
            public void onResponse(Call<GeoCode> call, Response<GeoCode> response) {
            }

            @Override
            public void onFailure(Call<GeoCode> call, Throwable t) {
            }
        });
    }
}
