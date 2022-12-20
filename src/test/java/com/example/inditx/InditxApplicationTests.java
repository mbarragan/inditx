package com.example.inditx;

import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.model.FareEntity;
import com.example.inditx.domain.ports.out.repository.FareRepository;
import com.example.inditx.util.Constants;
import com.example.inditx.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class InditxApplicationTests {

	@Autowired
	private FareRepository fareRepository;

	private final FareDTO fareDTOMock = Util.getFareDTOMock();

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T1000_thenOK() {
		Optional<FareEntity> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_1, Constants.FARE_DATETIME_MOCK_1, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareDTOMock, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T1600_thenOK() {
		Optional<FareEntity> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_2, Constants.FARE_DATETIME_MOCK_2, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareDTOMock, fareFoundOpt.get());
		Assertions.assertEquals(Constants.FARE_ID_MOCK_2, fareFoundOpt.get().getId());
		Assertions.assertEquals(Constants.FARE_AMOUNT_MOCK, fareFoundOpt.get().getPrice());
		Assertions.assertEquals(Constants.FARE_CURRENCY_MOCK, fareFoundOpt.get().getCurrency());
		Assertions.assertEquals(Constants.FARE_PRIORITY_MOCK, fareFoundOpt.get().getPriority());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T2100_thenOK() {
		Optional<FareEntity> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_3, Constants.FARE_DATETIME_MOCK_3, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareDTOMock, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210615T1000_thenOK() {
		FareDTO fareDTOMock_time4 = new FareDTO(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_4, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<FareEntity> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_4, Constants.FARE_DATETIME_MOCK_4, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareDTOMock_time4, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210616T2100_thenOK() {
		FareDTO fareDTOMock_time5 = new FareDTO(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_5, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<FareEntity> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_5, Constants.FARE_DATETIME_MOCK_5, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareDTOMock_time5, fareFoundOpt.get());
	}

	private void validateGeneralAssertions(FareDTO fareDTO, FareEntity fareFound) {

		Assertions.assertEquals(fareFound.getBrandId(), fareDTO.getBrandId());
		Assertions.assertEquals(fareFound.getProductId(), fareDTO.getProductId());
		Assertions.assertTrue(fareDTO.getStartDate().isAfter(fareFound.getStartDate()), "DateTimeMock is after fare startDate");
		Assertions.assertTrue(fareDTO.getStartDate().isBefore(fareFound.getEndDate()), "DateTimeMock is before fare endDate");
	}

}
