package org.jkutkut.hr_app.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CustomDate extends java.sql.Date {
    public CustomDate(long date) {
        super(date);
    }

    public CustomDate(int year, int month, int day) {
        super(year, month, day);
    }

    public static CustomDate fromDate(Date date) {
        return new CustomDate(date.getTime());
    }

    @Override
    public String toString() {
        return new SimpleDateFormat(DateUtil.FORMAT).format(this);
    }
}
