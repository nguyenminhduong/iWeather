package com.example.framgia.iweather.service;

/**
 * Created by Duong on 12/2/2016.
 */
public class APIConst {
    public class BaseURL {
        public static final String BASE_URL_FORECAST = "https://api.forecast.io/";
        public static final String BASE_URL_GEOCODE = "http://maps.googleapis.com/";
    }

    public class Param {
        public static final String PARAM_FORECAST = "location";
        public static final String PARAM_GEOCODE = "latlng";
    }

    public class Path {
        public static final String PATH_WEATHER =
            "forecast/" + APIKey.API_KEY_FORECAST + "/{location}";
        public static final String PATH_CITY_NAME = "maps/api/geocode/json?";
    }

    public class APIKey {
        public static final String API_KEY_FORECAST = "cd208eaa0e87df54194a5f3fc3eb43b8";
    }
}
