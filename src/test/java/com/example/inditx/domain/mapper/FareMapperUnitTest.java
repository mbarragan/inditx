package com.example.inditx.domain.mapper;

import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.model.FareEntity;
import com.example.inditx.util.Util;
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
    FareMapper fareMapper;

    @Test
    void mapPersistanceToApiFareTest() {

        FareEntity fareEntityMock = Util.getFareEntityMock();

        FareDTO expectedFareDTO = fareMapper.mapPersistenceToApi(fareEntityMock);

        assertThat("FareDTO id", expectedFareDTO.getId(), equalTo(fareEntityMock.getId()));
        assertThat("FareDTO brandId", expectedFareDTO.getBrandId(), equalTo(fareEntityMock.getBrandId()));
        assertThat("FareDTO productId", expectedFareDTO.getProductId(), equalTo(fareEntityMock.getProductId()));
        assertThat("FareDTO price", expectedFareDTO.getPrice(), equalTo(fareEntityMock.getPrice()));
        assertThat("FareDTO startDate", expectedFareDTO.getStartDate(), equalTo(fareEntityMock.getStartDate()));
        assertThat("FareDTO endDate", expectedFareDTO.getEndDate(), equalTo(fareEntityMock.getEndDate()));
    }

    @Test
    void mapApiToPersistanceUserTest() {
        FareDTO fareDTOMock = Util.getFareDTOMock();

        FareEntity expectedFareEntity = fareMapper.mapApiToPersistence(fareDTOMock);

        assertThat("FareEntity id", expectedFareEntity.getId(), equalTo(fareDTOMock.getId()));
        assertThat("FareEntity brandId", expectedFareEntity.getBrandId(), equalTo(fareDTOMock.getBrandId()));
        assertThat("FareEntity productId", expectedFareEntity.getProductId(), equalTo(fareDTOMock.getProductId()));
        assertThat("FareEntity price", expectedFareEntity.getPrice(), equalTo(fareDTOMock.getPrice()));
        assertThat("FareEntity startDate", expectedFareEntity.getStartDate(), equalTo(fareDTOMock.getStartDate()));
        assertThat("FareEntity endDate", expectedFareEntity.getEndDate(), equalTo(fareDTOMock.getEndDate()));
    }
}
