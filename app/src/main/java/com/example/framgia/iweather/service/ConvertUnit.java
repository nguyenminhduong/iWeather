package com.example.framgia.iweather.service;

import com.example.framgia.iweather.data.model.forecast.DataOfWeatherDate;
import com.example.framgia.iweather.data.model.forecast.Weather;
import com.example.framgia.iweather.utils.Const;

import static com.example.framgia.iweather.data.model.forecast.Weather.copyWeather;

public class ConvertUnit {
    private static final double CELSIUS_FINAL = 32;
    private static final double CELSIUS_TO_FAHR = 1.0 * 5 / 9;
    private static final double MILES_TO_METTER = 0.44704;
    private static final double MILES_TO_KM = 1.609344;

    private static double convertFahreiToCelsius(double fahre) {
        return (fahre - CELSIUS_FINAL) * CELSIUS_TO_FAHR;
    }

    private static double convertMilesPerHourToMetterPerSecond(double milesPerHour) {
        return milesPerHour * MILES_TO_METTER;
    }

    private static double convertMilesPerHourToKMPerHour(double milesPerHour) {
        return milesPerHour * MILES_TO_KM;
    }

    public static Weather convertWeather(Weather weather, String tempUnit, String windUnit) {
        Weather weatherTemp = copyWeather(weather);
        if (tempUnit.equals(Const.UnitTemp.CELSIUS)) {
            if (weatherTemp == null || weatherTemp.getCurrently() == null) return weatherTemp;
            if (weatherTemp.getDate() == null || weatherTemp.getDate().getData() == null)
                return weatherTemp;
            for (DataOfWeatherDate data : weatherTemp.getDate().getData()) {
                data.setTemperatureMax(convertFahreiToCelsius(data.getTemperatureMax()));
                data.setTemperatureMin(convertFahreiToCelsius(data.getTemperatureMin()));
            }
            weatherTemp.getCurrently()
                .setTemperature(convertFahreiToCelsius(weatherTemp.getCurrently()
                    .getTemperature()));
            weatherTemp.getCurrently()
                .setApparentTemperature(convertFahreiToCelsius(weatherTemp.getCurrently
                    ().getApparentTemperature()));
        }
        switch (windUnit) {
            case Const.UnitWind.METER_PER_SECOND:
                weatherTemp.getCurrently()
                    .setWindSpeed(convertMilesPerHourToMetterPerSecond(weatherTemp
                        .getCurrently().getWindSpeed()));
                break;
            case Const.UnitWind.KILOMETER_PER_HOUR:
                weatherTemp.getCurrently().setWindSpeed(convertMilesPerHourToKMPerHour(weatherTemp
                    .getCurrently().getWindSpeed()));
                break;
            default:
                break;
        }
        return weatherTemp;
    }
}
