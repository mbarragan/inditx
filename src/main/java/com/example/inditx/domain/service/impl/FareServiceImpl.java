package com.example.inditx.domain.service.impl;

import com.example.inditx.application.rest.mapper.FareMapper;
import com.example.inditx.infraestructure.model.Fare;
import com.example.inditx.domain.model.FareModel;
import com.example.inditx.domain.service.FareService;
import com.example.inditx.infraestructure.repository.FareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FareServiceImpl implements FareService {
    private static final Logger log = LoggerFactory.getLogger(FareServiceImpl.class);

    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private FareMapper fareMapper;

    @Transactional(readOnly = true)
    public Optional<FareModel> getFareByProductAndBrand(FareModel fareModel) {
        log.debug("Entering with fareModel {}", fareModel);
        Optional<Fare> fareOpt = fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            fareModel.getStartDate(), fareModel.getStartDate(), fareModel.getProductId(), fareModel.getBrandId());
        if (fareOpt.isEmpty()) {
            return Optional.empty();
        }
        log.debug("Leaving. Found {}", fareOpt.get());
        return Optional.of(fareMapper.mapPersistenceToApi( fareOpt.get()));
    }

}
