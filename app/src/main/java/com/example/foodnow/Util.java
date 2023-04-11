package com.example.foodnow;

import android.content.res.Resources;
import android.util.TypedValue;

public class Util {
    public static int convertDPtoPX(int dp, Resources resources) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
