package com.java.logisticssystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Delivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    private long deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private long lastUpdated;
}
