package com.example.inditx.domain.service.impl;

import com.example.inditx.application.rest.mapper.FareMapper;
import com.example.inditx.domain.model.FareModel;
import com.example.inditx.domain.service.FareService;
import com.example.inditx.infraestructure.model.Fare;
import com.example.inditx.infraestructure.repository.FareRepository;
import com.example.inditx.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FareServiceImplIntegrationTest {

    @TestConfiguration
    static class FareServiceImplIntegrationTestContextConfiguration {

        @Bean
        public FareService fareService() {
            return new FareServiceImpl();
        }
    }

    @Autowired
    private FareService fareService;

    @MockBean
    private FareMapper fareMapper;

    @MockBean
    private FareRepository fareRepository;

    private final FareModel fareModelMock = new FareModel(null, Constants.PRODUCT_ID_MOCK,
        Constants.FARE_DATETIME_MOCK_2, null, Constants.BRAND_ID_MOCK, null);;

    private final Fare fareMock = new Fare(1L, Constants.PRODUCT_ID_MOCK, Constants.FARE_START_DATETIME_MOCK,
        Constants.FARE_END_DATETIME_MOCK, Constants.BRAND_ID_MOCK, Constants.FARE_AMOUNT_MOCK,
        Constants.FARE_PRIORITY_MOCK, Constants.FARE_CURRENCY_MOCK);;

    @Test
    void whenValidFare_thenFareShouldBeFound() {

        when(fareMapper.mapPersistenceToApi(fareMock)).thenReturn( fareModelMock);
        when(fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
                Constants.FARE_DATETIME_MOCK_2, Constants.FARE_DATETIME_MOCK_2, Constants.PRODUCT_ID_MOCK,
                Constants.BRAND_ID_MOCK))
            .thenReturn(Optional.of( fareMock));

        Optional<FareModel> foundFareOpt = fareService.getFareByProductAndBrand(fareModelMock);

        Assertions.assertTrue(foundFareOpt.isPresent(), "foundFareOpt is present");
        Assertions.assertEquals(Constants.PRODUCT_ID_MOCK, foundFareOpt.get().getProductId());
        Assertions.assertEquals(Constants.BRAND_ID_MOCK, foundFareOpt.get().getBrandId());
        Assertions.assertEquals(Constants.FARE_DATETIME_MOCK_2, foundFareOpt.get().getStartDate());
    }

    @Test
    void whenInvalidFare_thenFareShouldNotBeFound() {
        when(fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            any(), any(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(Optional.empty());

        Optional<FareModel> foundFareOpt = fareService.getFareByProductAndBrand(fareModelMock);

        Assertions.assertFalse(foundFareOpt.isPresent(), "foundFareOpt is not present");
        Assertions.assertEquals(Optional.empty(), foundFareOpt);
    }

}
