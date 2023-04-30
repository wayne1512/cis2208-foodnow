package com.example.foodnow.data.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class ImageReader {
    public static Bitmap getRestaurantBanner(Context context, int id) {
        try {
            InputStream inputStream = context.getAssets().open("images/restaurantBanners/" + id + ".png");


            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
