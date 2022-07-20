package com.java.logisticssystem;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class ApplicationGlobalData
{
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate currentDate;

    static
    {
        currentDate = LocalDate.of(2021, 12, 14);
    }

    public static AtomicLong companyProfit = new AtomicLong(0);

    public static long getTimestampFromString(String dateString)
    {
        return LocalDate.parse(dateString, formatter).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public static String getStringFromTimestamp(long timestamp)
    {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
    }

    public static void moveToNextDay() {
        currentDate = currentDate.plusDays(1);
    }

    public static long getCurrentDateMilis() {
        return currentDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
