package com.example.inditx.infraestructure.adapter.in.controller;

import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.ports.in.service.FareService;
import com.example.inditx.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class FareControllerUnitTest {

    private final FareDTO fareDTOMock = Util.getFareDTOMock();

    @Test
    void  shouldGetFare() {
        FareService fareServiceMock = Mockito.mock(FareService.class);
        Mockito.when(fareServiceMock.getFareByProductAndBrand(any()))
            .thenReturn(Optional.of(fareDTOMock));

        FareController fareController = new FareController(fareServiceMock);
        Assertions.assertNotNull(fareController.getFare(fareDTOMock));
    }

    @Test()
    void shouldNotGetFare() {
        String actualMessage = com.example.inditx.domain.Constants.MESSAGE_FARE_NOT_FOUND;

        FareService fareServiceMock = Mockito.mock(FareService.class);
        Mockito.when(fareServiceMock.getFareByProductAndBrand(any()))
            .thenReturn(Optional.empty());

        FareController fareController = new FareController(fareServiceMock);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            fareController.getFare(fareDTOMock);
        });

        Assertions.assertEquals( exception.getMessage(),HttpStatus.NOT_FOUND.toString() + " \"" + actualMessage + "\"");
    }

}
