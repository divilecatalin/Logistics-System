package com.java.logisticssystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestinationDto
{
    private Long id;

    private String name;

    private Long distance;
}
