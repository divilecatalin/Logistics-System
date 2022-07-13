package com.java.logisticssystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ApplicationGlobalData
{
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static long currentDate;

    static
    {
        try
        {
            currentDate = getTimestampFromString("15-12-2021");
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static long companyProfit;

    public static long getTimestampFromString(String dateString) throws ParseException
    {
        return simpleDateFormat.parse(dateString).getTime();
    }

    public static String getStringFromTimestamp(long timestamp)
    {
        return simpleDateFormat.format(timestamp);
    }


}
