package com.example.framgia.iweather.service;

import com.example.framgia.iweather.data.model.forecast.Weather;
import com.example.framgia.iweather.data.model.geocode.GeoCode;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class API {
    private static final APIServices clientForecast = new Retrofit.Builder()
        .baseUrl(APIConst.BaseURL.BASE_URL_FORECAST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIServices.class);
    private static final APIServices clientGeocode = new Retrofit.Builder()
        .baseUrl(APIConst.BaseURL.BASE_URL_GEOCODE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIServices.class);

    public static void getWeather(String latlng,
                                  Callback<Weather> callback) {
        clientForecast.getWeather(latlng)
            .enqueue(callback);
    }

    public static void getCityName(String latlng,
                                   Callback<GeoCode> callback) {
        clientGeocode.getCityName(latlng)
            .enqueue(callback);
    }
}
