package com.example.foodnow;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class Restaurant {
    public String name;
    public String subtitle;
    public String image;
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

    public Restaurant(String name, String subtitle, String image, float lat, float lon, boolean isFav, String[] foodTypes) {
        this.name = name;
        this.subtitle = subtitle;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
        this.isFav = isFav;
        this.foodTypes = foodTypes;
    }
}
