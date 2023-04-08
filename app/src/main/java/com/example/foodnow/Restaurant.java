package com.example.foodnow;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class Restaurant {
    public int id;

    public String name;
    public String subtitle;
    public float lat;
    public float lon;
    public boolean isFav;
    public String[] foodTypes;

    public static HashMap<String, Integer> foodTypeDrawableMap = generateFoodTypeDrawableMap();

    private static HashMap<String, Integer> generateFoodTypeDrawableMap() {
        HashMap<String, Integer> map = new HashMap<>();


        map.put("burgers",R.drawable.ic_fooditem_burger_24dp);
        map.put("pizza",R.drawable.ic_fooditem_pizza_24dp);

        return map;
    }

    public Restaurant(int id, String name, String subtitle, float lat, float lon, boolean isFav, String[] foodTypes) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.lat = lat;
        this.lon = lon;
        this.isFav = isFav;
        this.foodTypes = foodTypes;
    }
}
