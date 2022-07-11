package com.java.logisticssystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Destination
{
    @Id
    private long id;

    @Column(unique = true)
    private String name;

    private long distance;

    @OneToMany(mappedBy = "destination", cascade=CascadeType.ALL)
    private List<Delivery> deliveries;
}
