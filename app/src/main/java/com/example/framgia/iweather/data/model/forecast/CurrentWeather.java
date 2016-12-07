package com.example.framgia.iweather.data.model.forecast;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("temperature")
    private Double mTemperature;
    @SerializedName("humidity")
    private Double mHumidity;
    @SerializedName("windSpeed")
    private Double mWindSpeed;
    @SerializedName("apparentTemperature")
    private Double mApparentTemperature;

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

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public Double getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }
}
