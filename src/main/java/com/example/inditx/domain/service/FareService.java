package com.example.inditx.domain.service;


import com.example.inditx.domain.model.FareModel;

import java.util.Optional;

public interface FareService {
    Optional<FareModel> getFareByProductAndBrand(FareModel fareModel);

}
