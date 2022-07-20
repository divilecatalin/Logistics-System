package com.java.logisticssystem.service;

import com.java.logisticssystem.ApplicationGlobalData;
import com.java.logisticssystem.model.Delivery;
import com.java.logisticssystem.model.Destination;
import com.java.logisticssystem.model.OrderStatus;
import com.java.logisticssystem.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.java.logisticssystem.model.OrderStatus.DELIVERED;
import static com.java.logisticssystem.model.OrderStatus.DELIVERING;

@Component
public class DeliveryProcessor
{
    private final DeliveryRepository deliveryRepository;

    private final Logger logger = LoggerFactory.getLogger(DeliveryProcessor.class);

    public DeliveryProcessor(DeliveryRepository deliveryRepository)
    {
        this.deliveryRepository = deliveryRepository;
    }

    @Async
    public void submitNewTask(Destination destination, List<Delivery> deliveries)
    {
        logger.info("Starting deliveries for " + destination.getName() + " at distance " + destination.getDistance() + " on thread " + Thread.currentThread().getName());
        deliveries.forEach(delivery -> changeDeliveryStatus(delivery, DELIVERING));
        try
        {
            Thread.sleep(destination.getDistance() * 200);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        deliveries.forEach(delivery -> changeDeliveryStatus(delivery, DELIVERED));
        int newProfit = deliveries.size();
        ApplicationGlobalData.companyProfit.addAndGet(newProfit);
        logger.info("Finished deliveries for " + destination.getName() + " at distance " + destination.getDistance() + " on thread " + Thread.currentThread().getName());
    }

    private void changeDeliveryStatus(Delivery delivery, OrderStatus delivering)
    {
        delivery.setStatus(delivering);
        deliveryRepository.save(delivery);
    }
}
