package com.example.framgia.iweather.data.model.forecast;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("currently")
    private CurrentWeather mCurrent;
    @SerializedName("daily")
    private WeatherDate mDate;

    public CurrentWeather getCurrently() {
        return mCurrent;
    }

    public void setCurrently(CurrentWeather current) {
        mCurrent = current;
    }

    public WeatherDate getDate() {
        return mDate;
    }

    public void setDate(WeatherDate date) {
        mDate = date;
    }
}
