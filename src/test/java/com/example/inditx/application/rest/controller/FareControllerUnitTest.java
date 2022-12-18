package com.example.inditx.application.rest.controller;

import com.example.inditx.domain.model.FareModel;
import com.example.inditx.domain.service.FareService;
import com.example.inditx.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class FareControllerUnitTest {

    @Test
    void  shouldGetFare() {
        FareModel fareModelMock = new FareModel(null, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_DATETIME_MOCK_2, null, Constants.BRAND_ID_MOCK, null);

        FareService fareServiceMock = Mockito.mock(FareService.class);
        Mockito.when(fareServiceMock.getFareByProductAndBrand(any()))
            .thenReturn(Optional.of(fareModelMock));

        FareController fareController = new FareController(fareServiceMock);
        Assertions.assertNotNull(fareController.getFare(fareModelMock));
    }

    @Test()
    void shouldNotGetFare() {
        String actualMessage = com.example.inditx.domain.Constants.MESSAGE_FARE_NOT_FOUND;
        FareModel fareModelMock = new FareModel(null, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_DATETIME_MOCK_2, null, Constants.BRAND_ID_MOCK, null);

        FareService fareServiceMock = Mockito.mock(FareService.class);
        Mockito.when(fareServiceMock.getFareByProductAndBrand(any()))
            .thenReturn(Optional.empty());

        FareController fareController = new FareController(fareServiceMock);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            fareController.getFare(fareModelMock);
        });

        Assertions.assertEquals( exception.getMessage(),HttpStatus.NOT_FOUND.toString() + " \"" + actualMessage + "\"");
    }

}
