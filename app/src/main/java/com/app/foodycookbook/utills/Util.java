package com.app.foodycookbook.utills;

import android.content.Context;
import android.view.View;

public class Util {
    public static String doFormatChange(String format, double value) {
        return String.format(format, value);
    }

    public static void doCangeToolBarColor(Context context, View view, int color) {
        view.setBackgroundColor(color);
    }
}
