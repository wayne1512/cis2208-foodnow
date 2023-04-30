package com.example.foodnow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.Task;

public class LocationHelper {

    private final Activity activity;

    private final FusedLocationProviderClient fusedLocationProviderClient;

    // LocationRequest - Requirements for the location updates, i.e.,
    // how often you should receive updates, the priority, etc.
    private LocationRequest locationRequest;

    // LocationCallback - Called when FusedLocationProviderClient
    // has a new Location
    private LocationCallback locationCallback;

    // This will store current location info
    private Location currentLocation;


    @SuppressLint("MissingPermission") // calling isLocationPermissionGranted instead
    public LocationHelper(Activity activity) {
        this.activity = activity;

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);

        isLocationPermissionGranted(activity);


    }

    @SuppressLint("MissingPermission")// calling isLocationPermissionGranted instead
    public Task<Location> getCurrentLocation() {
        if (isLocationPermissionGranted(activity)) {
            return fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null);
        } else return null;
    }

    public static boolean isLocationPermissionGranted(Activity ctx) {
        if (ActivityCompat.checkSelfPermission(
                ctx,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ctx,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    ctx,
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    0
            );
            return false;
        } else {
            return true;
        }
    }

}
