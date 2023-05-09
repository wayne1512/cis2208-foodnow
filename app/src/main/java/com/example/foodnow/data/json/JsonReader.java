package com.example.foodnow.data.json;

import android.content.Context;
import android.location.Location;

import com.example.foodnow.LocationHelper;
import com.example.foodnow.Restaurant;
import com.example.foodnow.Util;
import com.example.foodnow.backend.DbHelper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonReader {
    public static Task<Restaurant[]> readRestaurants(Context context, LocationHelper locationHelper) {
        String jsonString = "";
        try {
            //open the file
            InputStream inputStream = context.getAssets().open("raw/restaurants.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            //read the entire file.
            inputStream.read(buffer);
            inputStream.close();
            //store the contents in a string
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        //read the file
        Restaurant[] restaurants = gson.fromJson(jsonString, Restaurant[].class);


        //mark the restaurants in the favourites db table.
        ArrayList<Integer> favourites = new DbHelper(context).getFavourites();
        for (Integer favourite : favourites) {
            for (Restaurant restaurant : restaurants) {
                if (restaurant.id == favourite)
                    restaurant.isFav = true;
            }


        }

        //get the current location of the device
        Task<Location> currentLocTask = locationHelper.getCurrentLocation();
        //calculate distance
        if (currentLocTask != null) {
            return currentLocTask.continueWithTask(task -> {
                Location currentLoc = task.getResult();
                for (Restaurant restaurant : restaurants) {
                    //calculate the distance from the device to the restaurant
                    restaurant.distanceTo = Util.calculateDistance(currentLoc.getLatitude(), currentLoc.getLongitude(), restaurant.lat, restaurant.lon);
                }
                return Tasks.forResult(restaurants);
            });
        } else {
            return Tasks.forResult(restaurants);

        }

    }
}
