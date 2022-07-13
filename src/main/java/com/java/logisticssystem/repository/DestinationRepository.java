package com.java.logisticssystem.repository;

import com.java.logisticssystem.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long>
{
    Destination findByName(String name);
}
