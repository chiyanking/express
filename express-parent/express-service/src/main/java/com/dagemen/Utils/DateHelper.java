package com.dagemen.Utils;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/22.
 */
public class DateHelper {

    public static String getDateString(Date date) {
        return  String.valueOf(date.getTime());
    }
}
