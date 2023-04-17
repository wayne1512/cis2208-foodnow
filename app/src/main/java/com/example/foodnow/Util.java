package com.example.foodnow;

import android.content.res.Resources;
import android.location.Location;
import android.util.TypedValue;

public class Util {
    public static int convertDPtoPX(int dp, Resources resources) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static float calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        float[] res = new float[1];
        Location.distanceBetween(startLatitude,startLongitude,endLatitude,endLongitude,res);
        return res[0];
    }
}
