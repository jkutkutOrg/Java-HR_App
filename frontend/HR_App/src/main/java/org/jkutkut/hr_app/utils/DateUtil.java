package org.jkutkut.hr_app.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates.
 *
 * @author Jkutkut and kiol12
 */
public class DateUtil {
    private static final String DATE_PATTERN = "MM/dd/yyyy";
    public static final String FORMAT = DATE_PATTERN;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Formats the given date into a String.
     *
     * Note: null values are converted to an empty String.
     * Note: The format used is defined in the DATE_PATTERN constant.
     * @param date The date to be returned as a String
     * @return formatted String.
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date.toLocalDate());
    }

    /**
     * Converts a String in the format of the DATE_PATTERN to a LocalDate object.
     *
     * @param dateString The date as String
     * @return the date object or null if it could not be converted
     */
    public static CustomDate parse(String dateString) {
        try {
            String[] date = dateString.split("/");
            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);
            return new CustomDate(year, month, day);
        } catch (Exception e) {
            return null;
        }
    }
}