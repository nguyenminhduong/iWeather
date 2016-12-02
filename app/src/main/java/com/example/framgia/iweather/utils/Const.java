package com.example.framgia.iweather.utils;

/**
 * Created by chaupham on 12/1/2016.
 */
public class Const {
    public class IconWeather {
        public static final String CLEAR_DAY = "clear-day";
        public static final String CLEAR_NIGHT = "clear-night";
        public static final String CLOUDY = "cloudy";
        public static final String FOG = "fog";
        public static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
        public static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
        public static final String RAIN = "rain";
        public static final String SLEET = "sleet";
        public static final String SNOW = "snow";
        public static final String WIND = "wind";
    }

    public class RequestCode {
        public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;
        public static final int REQUEST_ACTION_LOCATION_SOURCE_SETTINGS = 1;
        public static final int REQUEST_ACTION_SETTINGS = 2;
    }

    public class UnitTemp {
        public static final String CELSIUS = "C";
        public static final String FAHRENHEIT = "F";
        public static final String PREF_TEMP_UNIT = "temp_unit";
    }

    public class UnitWind {
        public static final String MILES_PER_HOURS = "MPH";
        public static final String METER_PER_SECOND = "MPS";
        public static final String KILOMETER_PER_HOUR = "KPH";
        public static final String PREF_WIND_UNIT = "wind_unit";
    }
}
