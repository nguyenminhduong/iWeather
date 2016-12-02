package com.example.framgia.iweather.service;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;
import static com.example.framgia.iweather.utils.Const.RequestCode.REQUEST_ID_ACCESS_COURSE_FINE_LOCATION;

/**
 * Created by chaupham on 12/2/2016.
 */
public class LocationUtils {
    public static String getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService
            (LOCATION_SERVICE);
        Location lastLocation =
            locationManager
                .getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
        if (lastLocation == null) {
            lastLocation =
                locationManager.getLastKnownLocation
                    (LocationManager.PASSIVE_PROVIDER);
        }
        return lastLocation == null ? null : lastLocation
            .getLatitude() + "," + lastLocation.getLongitude();
    }

    public static boolean askPermissionsAndShowMyLocation(Context context) {
        if (ActivityCompat
            .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions((Activity) context, permissions,
                REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);
            return false;
        }
        return true;
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService
            (LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
