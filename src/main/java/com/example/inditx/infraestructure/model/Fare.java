package com.example.inditx.infraestructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_FARE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer brandId;
    private Double price;
    private Integer priority;
    private String currency;

}
