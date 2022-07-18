package com.java.logisticssystem.controller;

import com.java.logisticssystem.dto.DestinationDto;
import com.java.logisticssystem.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destination")
public class DestinationController
{
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService)
    {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<DestinationDto> handleGetAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDto> handleGetDestinationById(@PathVariable long id) {
        return destinationService.getDestination(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DestinationDto> handleDeleteDestinationById(@PathVariable long id) {
        return destinationService.deleteDestination(id);
    }

    @RequestMapping(method = { RequestMethod.POST,  RequestMethod.PUT })
    public DestinationDto handleSaveDestination(@RequestBody DestinationDto destinationDto) {
        return destinationService.saveOrEditDestination(destinationDto);
    }
}
