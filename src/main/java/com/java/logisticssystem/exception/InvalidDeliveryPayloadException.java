package com.java.logisticssystem.exception;

public class InvalidDeliveryPayloadException extends Exception
{
    public static final String INVALID_DESTINATION_ID = "Invalid destination ID";
    public static final String INVALID_DELIVERY_DATE_FORMAT = "Invalid delivery date format, should be dd-MM-yyyy";
    public static final String INVALID_DELIVERY_DATE = "Invalid delivery date, should be later than current date";

    public InvalidDeliveryPayloadException(String message)
    {
        super(message);
    }
}
