package com.example.inditx.aplication.service;

import com.example.inditx.domain.mapper.FareMapper;
import com.example.inditx.domain.model.FareEntity;
import com.example.inditx.domain.model.FareDTO;
import com.example.inditx.domain.ports.in.service.FareService;
import com.example.inditx.domain.ports.out.repository.FareRepository;
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
    public Optional<FareDTO> getFareByProductAndBrand(FareDTO fareDTO) {
        log.debug("Entering with fareDTO {}", fareDTO);
        Optional<FareEntity> fareEntityOpt = fareRepository.findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            fareDTO.getStartDate(), fareDTO.getStartDate(), fareDTO.getProductId(), fareDTO.getBrandId());
        if (fareEntityOpt.isEmpty()) {
            return Optional.empty();
        }
        log.debug("Leaving. Found {}", fareEntityOpt.get());
        return Optional.of(fareMapper.mapPersistenceToApi( fareEntityOpt.get()));
    }

}
