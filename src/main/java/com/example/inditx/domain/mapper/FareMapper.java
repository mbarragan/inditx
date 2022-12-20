package com.example.inditx.domain.mapper;

import com.example.inditx.domain.model.FareEntity;
import com.example.inditx.domain.model.FareDTO;
import org.springframework.stereotype.Component;

@Component
public class FareMapper {

    public FareEntity mapApiToPersistence(FareDTO fareDTO) {
        FareEntity fareEntity = new FareEntity();
        fareEntity.setId(fareDTO.getId());
        fareEntity.setBrandId(fareDTO.getBrandId());
        fareEntity.setPrice(fareDTO.getPrice());
        fareEntity.setStartDate(fareDTO.getStartDate());
        fareEntity.setEndDate(fareDTO.getEndDate());
        fareEntity.setProductId(fareDTO.getProductId());
        return fareEntity;
    }

    public FareDTO mapPersistenceToApi(FareEntity fareEntity) {
        FareDTO fareDTO = new FareDTO();
        fareDTO.setId(fareEntity.getId());
        fareDTO.setPrice(fareEntity.getPrice());
        fareDTO.setBrandId(fareEntity.getBrandId());
        fareDTO.setEndDate(fareEntity.getEndDate());
        fareDTO.setProductId(fareEntity.getProductId());
        fareDTO.setStartDate(fareEntity.getStartDate());
        return fareDTO;
    }
}
