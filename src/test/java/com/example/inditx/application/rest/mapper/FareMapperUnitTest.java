package com.example.inditx.application.rest.mapper;

import com.example.inditx.domain.model.FareModel;
import com.example.inditx.infraestructure.model.Fare;
import com.example.inditx.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
class FareMapperUnitTest {

    @TestConfiguration
    static class TemplateMapperTestContextConfiguration
    {
        @Bean
        public FareMapper templateMapper()
        {
            return new FareMapper();
        }
    }

    @Autowired
    FareMapper      fareMapper;

    @Test
    void mapPersistanceToApiFareTest() {

        Fare fareMock = new Fare(Constants.FARE_ID_MOCK_2, Constants.PRODUCT_ID_MOCK, Constants.FARE_START_DATETIME_MOCK,
            Constants.FARE_END_DATETIME_MOCK, Constants.BRAND_ID_MOCK, Constants.FARE_AMOUNT_MOCK,
            Constants.FARE_PRIORITY_MOCK, Constants.FARE_CURRENCY_MOCK);

        FareModel expectedFareModel = fareMapper.mapPersistenceToApi(fareMock);

        assertThat("Fare id", expectedFareModel.getId(), equalTo(fareMock.getId()));
        assertThat("Fare brandId", expectedFareModel.getBrandId(), equalTo(fareMock.getBrandId()));
        assertThat("Fare productId", expectedFareModel.getProductId(), equalTo(fareMock.getProductId()));
        assertThat("Fare price", expectedFareModel.getPrice(), equalTo(fareMock.getPrice()));
        assertThat("Fare startDate", expectedFareModel.getStartDate(), equalTo(fareMock.getStartDate()));
        assertThat("Fare endDate", expectedFareModel.getEndDate(), equalTo(fareMock.getEndDate()));
    }

    @Test
    void mapApiToPersistanceUserTest() {
        FareModel fareModelMock = new FareModel(Constants.FARE_ID_MOCK_2, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_START_DATETIME_MOCK, Constants.FARE_END_DATETIME_MOCK, Constants.BRAND_ID_MOCK,
            Constants.FARE_AMOUNT_MOCK);

        Fare expectedFare = fareMapper.mapApiToPersistence(fareModelMock);

        assertThat("Fare id", expectedFare.getId(), equalTo(fareModelMock.getId()));
        assertThat("Fare brandId", expectedFare.getBrandId(), equalTo(fareModelMock.getBrandId()));
        assertThat("Fare productId", expectedFare.getProductId(), equalTo(fareModelMock.getProductId()));
        assertThat("Fare price", expectedFare.getPrice(), equalTo(fareModelMock.getPrice()));
        assertThat("Fare startDate", expectedFare.getStartDate(), equalTo(fareModelMock.getStartDate()));
        assertThat("Fare endDate", expectedFare.getEndDate(), equalTo(fareModelMock.getEndDate()));
    }
}
