package com.example.inditx;

import com.example.inditx.domain.model.FareModel;
import com.example.inditx.infraestructure.model.Fare;
import com.example.inditx.infraestructure.repository.FareRepository;
import com.example.inditx.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class InditxApplicationTests {

	@Autowired
	private FareRepository fareRepository;

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T1000_thenOK() {
		FareModel fareModel = new FareModel(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_1, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<Fare> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_1, Constants.FARE_DATETIME_MOCK_1, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareModel, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T1600_thenOK() {
		FareModel fareModel = new FareModel(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_2, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<Fare> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_2, Constants.FARE_DATETIME_MOCK_2, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareModel, fareFoundOpt.get());
		Assertions.assertEquals(Constants.FARE_ID_MOCK_2, fareFoundOpt.get().getId());
		Assertions.assertEquals(Constants.FARE_AMOUNT_MOCK, fareFoundOpt.get().getPrice());
		Assertions.assertEquals(Constants.FARE_CURRENCY_MOCK, fareFoundOpt.get().getCurrency());
		Assertions.assertEquals(Constants.FARE_PRIORITY_MOCK, fareFoundOpt.get().getPriority());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210614T2100_thenOK() {
		FareModel fareModel = new FareModel(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_3, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<Fare> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_3, Constants.FARE_DATETIME_MOCK_3, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareModel, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210615T1000_thenOK() {
		FareModel fareModel = new FareModel(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_4, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<Fare> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_4, Constants.FARE_DATETIME_MOCK_4, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareModel, fareFoundOpt.get());
	}

	@Test
	void givenFareRepository_whenRetrieveEntityBy_20210616T2100_thenOK() {
		FareModel fareModel = new FareModel(null, Constants.PRODUCT_ID_MOCK, Constants.FARE_DATETIME_MOCK_5, null,
			Constants.BRAND_ID_MOCK, null);
		Optional<Fare> fareFoundOpt =
			fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
				Constants.FARE_DATETIME_MOCK_5, Constants.FARE_DATETIME_MOCK_5, Constants.PRODUCT_ID_MOCK,
				Constants.BRAND_ID_MOCK);

		Assertions.assertTrue(fareFoundOpt.isPresent());
		validateGeneralAssertions(fareModel, fareFoundOpt.get());
	}

	private void validateGeneralAssertions(FareModel fareModel, Fare fareFound) {

		Assertions.assertEquals(fareFound.getBrandId(), fareModel.getBrandId());
		Assertions.assertEquals(fareFound.getProductId(), fareModel.getProductId());
		Assertions.assertTrue(fareModel.getStartDate().isAfter(fareFound.getStartDate()), "DateTimeMock is after fare startDate");
		Assertions.assertTrue(fareModel.getStartDate().isBefore(fareFound.getEndDate()), "DateTimeMock is before fare endDate");
	}

}
