package com.example.framgia.iweather.service;

import com.example.framgia.iweather.data.model.forecast.Weather;
import com.example.framgia.iweather.data.model.geocode.GeoCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {

    @GET(APIConst.Path.PATH_WEATHER)
    Call<Weather> getWeather(@Path(APIConst.Param.PARAM_FORECAST) String location);

    @POST(APIConst.Path.PATH_CITY_NAME)
    Call<GeoCode> getCityName(@Query(APIConst.Param.PARAM_GEOCODE) String location);
}

