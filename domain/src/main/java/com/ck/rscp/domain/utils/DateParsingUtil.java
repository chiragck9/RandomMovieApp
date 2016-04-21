package com.ck.rscp.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ckunder on 19-04-2016.
 */
public class DateParsingUtil {

    public static String getDateString(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
