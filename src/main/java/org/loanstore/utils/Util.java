package org.loanstore.utils;

import org.loanstore.exceptions.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    public static Date parseDate(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            System.out.printf("ParseException occurred when converting %s to %s simple date format%n", date, DATE_PATTERN);
            throw new InvalidDateException(String.format("Invalid date format: %s", date));
        }
    }

    public static String getCurrentDate() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }
}
