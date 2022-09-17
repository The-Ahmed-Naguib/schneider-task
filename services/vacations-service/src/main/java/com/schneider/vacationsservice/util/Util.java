package com.schneider.vacationsservice.util;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Util {

    public static String getDateAsString(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

    @SneakyThrows
    public static Date getDateFromString(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
