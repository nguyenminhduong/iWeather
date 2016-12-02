package com.example.framgia.iweather.data.model.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherDate {
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("data")
    private List<DataOfWeatherDate> mData = new ArrayList<DataOfWeatherDate>();

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public List<DataOfWeatherDate> getData() {
        return mData;
    }

    public void setData(List<DataOfWeatherDate> data) {
        mData = data;
    }
}
