package com.java.logisticssystem.service;

import com.java.logisticssystem.ApplicationGlobalData;
import com.java.logisticssystem.converter.DeliveryConverter;
import com.java.logisticssystem.dto.DeliveryDto;
import com.java.logisticssystem.exception.InvalidDeliveryPayloadException;
import com.java.logisticssystem.model.Delivery;
import com.java.logisticssystem.model.Destination;
import com.java.logisticssystem.model.OrderStatus;
import com.java.logisticssystem.repository.DeliveryRepository;
import com.java.logisticssystem.repository.DestinationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.java.logisticssystem.exception.InvalidDeliveryPayloadException.INVALID_DELIVERY_DATE;
import static com.java.logisticssystem.exception.InvalidDeliveryPayloadException.INVALID_DESTINATION_ID;

@Service
public class DeliveryService
{
    private final DeliveryRepository deliveryRepository;
    private final DestinationRepository destinationRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DestinationRepository destinationRepository)
    {
        this.deliveryRepository = deliveryRepository;
        this.destinationRepository = destinationRepository;
    }

    public DeliveryDto addDelivery(DeliveryDto deliveryDto) throws InvalidDeliveryPayloadException, ParseException
    {
        Delivery delivery = new Delivery();

        long deliveryTime = ApplicationGlobalData.simpleDateFormat.parse(deliveryDto.getDeliveryDate()).getTime();
        if (deliveryTime < ApplicationGlobalData.currentDate) {
            throw new InvalidDeliveryPayloadException(INVALID_DELIVERY_DATE);
        }

        Optional<Destination> optionalDestination = destinationRepository.findById(deliveryDto.getDestinationId());
        delivery.setDestination(optionalDestination.orElseThrow(() -> new InvalidDeliveryPayloadException(INVALID_DESTINATION_ID)));


        delivery.setDeliveryDate(deliveryTime);

        delivery.setStatus(OrderStatus.NEW);
        delivery.setLastUpdated(new Date().getTime());

        deliveryRepository.save(delivery);

        return DeliveryConverter.deliveryToDeliveryDto(delivery);
    }

    public ResponseEntity<List<DeliveryDto>> addDeliveries(List<DeliveryDto> deliveryDtoList) throws InvalidDeliveryPayloadException, ParseException
    {
        List<DeliveryDto> addedDeliveries = new ArrayList<>();
        for (DeliveryDto deliveryDto : deliveryDtoList)
        {
            addedDeliveries.add(addDelivery(deliveryDto));
        }
        return new ResponseEntity<>(addedDeliveries, HttpStatus.OK);
    }

    public ResponseEntity<List<DeliveryDto>> findDeliveriesByDateAndDestination(String date, String destination) throws ParseException
    {
        long dateTimeStamp;
        if (date != null)
        {
            dateTimeStamp = ApplicationGlobalData.getTimestampFromString(date);
        } else {
            dateTimeStamp = ApplicationGlobalData.currentDate;
        }
        List<Delivery> foundDeliveries = deliveryRepository.findAllByDeliveryDateAndDestination_NameContainingIgnoreCase(dateTimeStamp, destination);
        return new ResponseEntity<>(DeliveryConverter.deliveryListToDtoList(foundDeliveries), HttpStatus.OK);
    }

    public void loadDeliveryCsv() throws ParseException, InvalidDeliveryPayloadException
    {
        deliveryRepository.deleteAll();
        List<DeliveryDto> deliveries = new ArrayList<>();
        try
        {
            File file=new File("src/main/resources/deliveries.csv");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line=br.readLine())!=null)
            {
                String [] tokens = line.split(",");
                if (tokens.length != 2) {
                    continue;
                }
                DeliveryDto deliveryDto = new DeliveryDto();
                deliveryDto.setDeliveryDate(tokens[1]);
                Destination destination = destinationRepository.findByName(tokens[0]);
                if (destination == null) {
                    continue;
                }
                deliveryDto.setDestinationId(destination.getId());
                deliveries.add(deliveryDto);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        addDeliveries(deliveries);
    }
}
