package com.java.logisticssystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Delivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    private long deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private long lastUpdated;
}
