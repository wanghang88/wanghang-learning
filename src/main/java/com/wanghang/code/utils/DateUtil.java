package com.wanghang.code.utils;



import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDD = "yyyy-MM-dd";


    /**
     * 时间格式化
     * @param date
     * @param formatPattern
     * @return java.lang.String
     * @Date 2018/04/28
     */
    public static String format(Date date, String formatPattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
        return f.format(date);
    }


    public static Date parse(String date, String formatPattern) throws ParseException {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
        return f.parse(date);
    }
}
