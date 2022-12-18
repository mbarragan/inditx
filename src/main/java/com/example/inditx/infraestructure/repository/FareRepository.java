package com.example.inditx.infraestructure.repository;

import com.example.inditx.infraestructure.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

    Optional<Fare> findFirstByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime before,
                                                                                                      LocalDateTime after,
                                                                                                      Integer productId,
                                                                                                      Integer brandId);

}
