package com.example.inditx.application.service;

import com.example.inditx.aplication.service.FareServiceImpl;
import com.example.inditx.domain.mapper.FareMapper;
import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.ports.in.service.FareService;
import com.example.inditx.domain.model.FareEntity;
import com.example.inditx.domain.ports.out.repository.FareRepository;
import com.example.inditx.util.Constants;
import com.example.inditx.util.Util;
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

    private final FareDTO fareDTOMock = Util.getFareDTOMock();

    private final FareEntity fareEntityMock = Util.getFareEntityMock();

    @Test
    void whenValidFare_thenFareShouldBeFound() {

        when(fareMapper.mapPersistenceToApi(fareEntityMock)).thenReturn( fareDTOMock);
        when(fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
                Constants.FARE_DATETIME_MOCK_2, Constants.FARE_DATETIME_MOCK_2, Constants.PRODUCT_ID_MOCK,
                Constants.BRAND_ID_MOCK))
            .thenReturn(Optional.of( fareEntityMock));

        Optional<FareDTO> foundFareDtoOpt = fareService.getFareByProductAndBrand(fareDTOMock);

        Assertions.assertTrue(foundFareDtoOpt.isPresent(), "foundFareDtoOpt is present");
        Assertions.assertEquals(Constants.PRODUCT_ID_MOCK, foundFareDtoOpt.get().getProductId());
        Assertions.assertEquals(Constants.BRAND_ID_MOCK, foundFareDtoOpt.get().getBrandId());
        Assertions.assertEquals(Constants.FARE_DATETIME_MOCK_2, foundFareDtoOpt.get().getStartDate());
    }

    @Test
    void whenInvalidFare_thenFareShouldNotBeFound() {
        when(fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            any(), any(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(Optional.empty());

        Optional<FareDTO> foundFareDtoOpt = fareService.getFareByProductAndBrand(fareDTOMock);

        Assertions.assertFalse(foundFareDtoOpt.isPresent(), "foundFareDtoOpt is not present");
        Assertions.assertEquals(Optional.empty(), foundFareDtoOpt);
    }

}
