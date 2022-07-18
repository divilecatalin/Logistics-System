package com.java.logisticssystem.repository;

import com.java.logisticssystem.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{
    List<Delivery> findAllByDeliveryDateAndDestination_NameContainingIgnoreCase(long deliveryDate, String destination);
    List<Delivery> findAllByDeliveryDate(long deliveryDate);
}
