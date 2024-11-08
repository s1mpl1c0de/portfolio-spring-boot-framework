package com.simplicode.portfolio.util;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarUtil {

    public static String getMonthAbbr(Short number) {
        return number != null ? Month.of(number).getDisplayName(TextStyle.SHORT, Locale.ENGLISH) : null;
    }

}
