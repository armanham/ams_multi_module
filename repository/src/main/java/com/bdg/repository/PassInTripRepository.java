package com.bdg.repository;

import com.bdg.domain.persistent.PassInTripPer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassInTripRepository extends JpaRepository<PassInTripPer, Long> {

    boolean existsByPassenger_Id(Long passengerId);

    boolean existsByPassenger_Name(String name);

    boolean existsByPassenger_Phone(String phone);
}