package com.example.foodnow;

import java.io.Serializable;
import java.util.HashMap;


public class Restaurant implements Serializable {
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
        map.put("pasta",R.drawable.ic_fooditem_pasta_24dp);
        map.put("vegetarian",R.drawable.ic_fooditem_vegetarian_24dp);
        map.put("desert",R.drawable.ic_fooditem_desert_24dp);
        map.put("meat",R.drawable.ic_fooditem_meat_24dp);
        map.put("sandwiches",R.drawable.ic_fooditem_sandwich_24dp);
        map.put("coffee",R.drawable.ic_fooditem_coffee_24dp);

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
