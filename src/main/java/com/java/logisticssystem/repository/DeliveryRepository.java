package com.java.logisticssystem.repository;

import com.java.logisticssystem.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{
}
