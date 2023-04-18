package com.example.foodnow.backend;

import android.provider.BaseColumns;

public class FavoriteContract {
    private FavoriteContract(){}

    public static class FavouriteEntry implements BaseColumns{
        public static final String TABLE_NAME = "favourites";
        public static final String COLUMN_NAME_RESTAURANT_ID = "restaurantId";
    }
}
