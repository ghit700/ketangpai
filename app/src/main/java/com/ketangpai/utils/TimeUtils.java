package com.ketangpai.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/14.
 */
public class TimeUtils {

    public static String getCurrentTimeFormat(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(calendar.getTimeInMillis());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDateFormat(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(calendar.getTimeInMillis());
        return simpleDateFormat.format(date);
    }

    public static String getNoticeTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    public static String getNotificationTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
    public static String getNewestMessageTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    ;
}
