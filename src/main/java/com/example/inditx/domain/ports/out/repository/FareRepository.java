package com.example.inditx.domain.ports.out.repository;

import com.example.inditx.domain.model.FareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FareRepository extends JpaRepository<FareEntity, Long> {

    Optional<FareEntity> findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime before,
                                                                                                            LocalDateTime after,
                                                                                                            Integer productId,
                                                                                                            Integer brandId);

}
