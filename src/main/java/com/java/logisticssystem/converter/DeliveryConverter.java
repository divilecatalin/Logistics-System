package com.java.logisticssystem.converter;

import com.java.logisticssystem.ApplicationGlobalData;
import com.java.logisticssystem.dto.DeliveryDto;
import com.java.logisticssystem.dto.DestinationDto;
import com.java.logisticssystem.model.Delivery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryConverter
{
    public static DeliveryDto deliveryToDeliveryDto(Delivery delivery)
    {
        DeliveryDto deliveryDto = new DeliveryDto();

        deliveryDto.setId(delivery.getId());
        deliveryDto.setDestination(DestinationDto.builder()
                .id(delivery.getDestination().getId())
                .distance(delivery.getDestination().getDistance())
                .name(delivery.getDestination().getName())
                .build());
        deliveryDto.setStatus(delivery.getStatus());
        deliveryDto.setDestinationId(delivery.getDestination().getId());
        deliveryDto.setDeliveryDate(ApplicationGlobalData.getStringFromTimestamp(delivery.getDeliveryDate()));

        return deliveryDto;
    }

    public static List<DeliveryDto> deliveryListToDtoList(List<Delivery> deliveries) throws ParseException
    {
        List<DeliveryDto> dtos = new ArrayList<>();
        for (Delivery delivery : deliveries)
        {
            dtos.add(deliveryToDeliveryDto(delivery));
        }
        return dtos;
    }
}
