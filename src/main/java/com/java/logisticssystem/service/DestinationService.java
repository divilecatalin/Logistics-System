package com.java.logisticssystem.service;

import com.java.logisticssystem.converter.DestinationConverter;
import com.java.logisticssystem.dto.DestinationDto;
import com.java.logisticssystem.model.Destination;
import com.java.logisticssystem.repository.DestinationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.java.logisticssystem.converter.DestinationConverter.destinationDtoToDestinationModel;
import static com.java.logisticssystem.converter.DestinationConverter.destinationModelToDestinationDto;

@Service
public class DestinationService
{
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository)
    {
        this.destinationRepository = destinationRepository;
    }

    public List<DestinationDto> getAllDestinations()
    {
        return DestinationConverter.destinationModelListToDestinationDtoList(destinationRepository.findAll());
    }

    public ResponseEntity<DestinationDto> getDestination(long id)
    {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            return new ResponseEntity<>(destinationModelToDestinationDto(optionalDestination.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * @param id destination ID from DB
     * @return OK if deleted, 404 NOT FOUND if the id does not exist
     */
    public ResponseEntity<DestinationDto> deleteDestination(long id)
    {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            destinationRepository.delete(optionalDestination.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public DestinationDto saveOrEditDestination(DestinationDto destinationDto)
    {
        Destination destination = destinationDtoToDestinationModel(destinationDto);
        destinationRepository.save(destination);
        return destinationModelToDestinationDto(destination);
    }
}
