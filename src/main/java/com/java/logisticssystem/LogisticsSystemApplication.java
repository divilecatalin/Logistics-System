package com.java.logisticssystem;

import com.java.logisticssystem.exception.InvalidDeliveryPayloadException;
import com.java.logisticssystem.service.DeliveryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.text.ParseException;

@SpringBootApplication
@EnableAsync
public class LogisticsSystemApplication {

	public static void main(String[] args) throws ParseException, InvalidDeliveryPayloadException
	{
		ApplicationContext applicationContext = SpringApplication.run(LogisticsSystemApplication.class, args);
		DeliveryService deliveryService = applicationContext.getBean(DeliveryService.class);
		deliveryService.loadDeliveryCsv();
	}

}
