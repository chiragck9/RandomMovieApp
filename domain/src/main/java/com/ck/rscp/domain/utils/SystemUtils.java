package com.ck.rscp.domain.utils;

import android.app.Activity;
import android.content.Context;
import android.provider.CalendarContract;
import android.util.DisplayMetrics;

/**
 * Created by ckunder on 21-04-2016.
 */
public class SystemUtils {

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }
}
