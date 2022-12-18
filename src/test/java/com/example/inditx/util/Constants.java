package com.example.inditx.util;

import java.time.LocalDateTime;
import java.time.Month;

public class Constants {
    public static final LocalDateTime FARE_DATETIME_MOCK_1 = LocalDateTime.of(2020, Month.JUNE,14,10,0,0);
    public static final LocalDateTime FARE_DATETIME_MOCK_2 = LocalDateTime.of(2020, Month.JUNE,14,16,0,0);
    public static final LocalDateTime FARE_DATETIME_MOCK_3 = LocalDateTime.of(2020, Month.JUNE,14,21,0,0);
    public static final LocalDateTime FARE_DATETIME_MOCK_4 = LocalDateTime.of(2020, Month.JUNE,15,10,0,0);
    public static final LocalDateTime FARE_DATETIME_MOCK_5 = LocalDateTime.of(2020, Month.JUNE,16,21,0,0);

    public static final Integer BRAND_ID_MOCK = 1;
    public static final Integer PRODUCT_ID_MOCK = 35455;

    //Data for entry #2
    public static final Long FARE_ID_MOCK_2 = 2L;
    public static final LocalDateTime FARE_START_DATETIME_MOCK = LocalDateTime.of(2020, Month.JUNE,14,15,0,0);
    public static final LocalDateTime FARE_END_DATETIME_MOCK = LocalDateTime.of(2020, Month.JUNE,14,18,30,0);
    public static final Integer FARE_PRIORITY_MOCK = 1;
    public static final Double FARE_AMOUNT_MOCK = 25.45;
    public static final String FARE_CURRENCY_MOCK = "EUR";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

}
