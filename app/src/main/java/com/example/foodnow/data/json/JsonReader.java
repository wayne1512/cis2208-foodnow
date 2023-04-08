package com.example.foodnow.data.json;

import android.content.Context;

import com.example.foodnow.Restaurant;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    public static Restaurant[] readRestaurants(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/restaurants.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Restaurant[].class);
    }
}
