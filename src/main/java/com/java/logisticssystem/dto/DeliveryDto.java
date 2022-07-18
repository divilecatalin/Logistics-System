package com.java.logisticssystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.logisticssystem.ApplicationGlobalData;
import com.java.logisticssystem.model.OrderStatus;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static com.java.logisticssystem.exception.InvalidDeliveryPayloadException.INVALID_DELIVERY_DATE_FORMAT;

@Data
public class DeliveryDto
{
    private long id;

    @NotNull
    @Min(1)
    private long destinationId;

    private DestinationDto destination;

    @NotNull
    private String deliveryDate;

    private OrderStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @AssertTrue(message = INVALID_DELIVERY_DATE_FORMAT)
    public boolean isDeliveryDateFormatValid() {
        try
        {
            ApplicationGlobalData.formatter.parse(this.getDeliveryDate());
        } catch (DateTimeParseException e)
        {
            return false;
        }
        return true;
    }

}
