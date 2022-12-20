package com.example.inditx.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FareDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("productId")
    private Integer productId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("startDate")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    @JsonProperty("brandId")
    private Integer brandId;

    @JsonProperty("price")
    private Double price;

}
