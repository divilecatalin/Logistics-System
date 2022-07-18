package com.java.logisticssystem.converter;

import com.java.logisticssystem.dto.DestinationDto;
import com.java.logisticssystem.model.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationConverter
{
    public static DestinationDto destinationModelToDestinationDto(Destination destination) {
        return DestinationDto.builder()
                .id(destination.getId())
                .name(destination.getName())
                .distance(destination.getDistance())
                .build();
    }

    public static List<DestinationDto> destinationModelListToDestinationDtoList(List<Destination> destinations) {
        List<DestinationDto> destinationDtos = new ArrayList<>();
        destinations.forEach(destination -> destinationDtos.add(destinationModelToDestinationDto(destination)));
        return destinationDtos;
    }

    public static Destination destinationDtoToDestinationModel(DestinationDto destinationDto) {
        Destination destination = new Destination();
        if (destinationDto.getId() != null) {
            destination.setId(destinationDto.getId());
        }
        destination.setName(destinationDto.getName());
        destination.setDistance(destinationDto.getDistance());
        return destination;
    }
}
