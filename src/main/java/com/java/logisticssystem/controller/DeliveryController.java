package com.java.logisticssystem.controller;

import com.java.logisticssystem.dto.DeliveryDto;
import com.java.logisticssystem.exception.InvalidDeliveryPayloadException;
import com.java.logisticssystem.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/delivery")
public class DeliveryController
{
    private final DeliveryService deliveryservice;

    public DeliveryController(DeliveryService deliveryservice)
    {
        this.deliveryservice = deliveryservice;
    }

    @PostMapping("/add")
    public ResponseEntity<List<DeliveryDto>> handleDeliveryAdd(@Valid @RequestBody List<DeliveryDto> deliveryDtoList) throws InvalidDeliveryPayloadException, ParseException
    {
        return deliveryservice.addDeliveries(deliveryDtoList);
    }

    @GetMapping("/status")
    public ResponseEntity<List<DeliveryDto>> handleGetDeliveries(@RequestParam(required = false) @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$") String date,
                                                                 @RequestParam(defaultValue = "") String destination) throws ParseException
    {
        return deliveryservice.findDeliveriesByDateAndDestination(date, destination);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ InvalidDeliveryPayloadException.class , ConstraintViolationException.class})
    public String handleInvalidDeliveryPayloadException(Exception ex) {
        return ex.getMessage();
    }
}
