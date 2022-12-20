package com.example.inditx.util;

import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.model.FareEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {

    /**
     * Correct an issue when the ISO LocalDateTime object has zero seconds
     * @param dateTime LocalDateTime
     * @return String the corrected time with seconds
     */
    public static String withSeconds(LocalDateTime dateTime) {
        // formatter with required seconds
        DateTimeFormatter withSecs = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT);
        try {
            LocalDateTime.parse(dateTime.toString(), withSecs);
        } catch (DateTimeParseException e) {
            return dateTime + ":00";
        }
        return dateTime.toString();
    }

    public static FareDTO getFareDTOMock() {
        return new FareDTO(null, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_DATETIME_MOCK_2, null, Constants.BRAND_ID_MOCK, null);
    }

    public static FareEntity getFareEntityMock() {
        return new FareEntity(1L, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_START_DATETIME_MOCK,
            Constants.FARE_END_DATETIME_MOCK, Constants.BRAND_ID_MOCK, Constants.FARE_AMOUNT_MOCK,
            Constants.FARE_PRIORITY_MOCK, Constants.FARE_CURRENCY_MOCK);
    }
}
