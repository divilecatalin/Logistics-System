package com.java.logisticssystem.model;

import javax.persistence.*;

@Entity
public class Delivery
{
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    private long deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private long lastUpdated;
}
