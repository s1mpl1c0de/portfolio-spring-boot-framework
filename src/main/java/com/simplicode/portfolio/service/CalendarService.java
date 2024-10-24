package com.simplicode.portfolio.service;

import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class CalendarService {

    public String getMonthAbbr(Short number) {
        return number != null ? Month.of(number).getDisplayName(TextStyle.SHORT, Locale.ENGLISH) : null;
    }

}
