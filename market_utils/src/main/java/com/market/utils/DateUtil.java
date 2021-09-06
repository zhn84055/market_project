package com.market.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * String转date
     *
     * @param dateStr
     * @param dateFormatStr
     * @return
     */
    public static Date str2Date(String dateStr, String dateFormatStr) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date转String
     *
     * @param date
     * @param dateFormatStr
     * @return
     */
    public static String date2Str(Date date, String dateFormatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        String formatStr = dateFormat.format(date);
        return formatStr;
    }
}
