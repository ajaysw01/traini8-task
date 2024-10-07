package com.ajaysw.repository;

import com.ajaysw.model.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {

    List<TrainingCenter> findByAddress_City(String city);

    List<TrainingCenter> findByAddress_State(String state);
}
