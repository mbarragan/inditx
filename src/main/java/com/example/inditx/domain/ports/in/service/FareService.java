package com.example.inditx.domain.ports.in.service;


import com.example.inditx.domain.model.FareDTO;

import java.util.Optional;

public interface FareService {
    Optional<FareDTO> getFareByProductAndBrand(FareDTO fareModel);

}
