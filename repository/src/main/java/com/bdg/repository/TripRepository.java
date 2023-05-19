package com.bdg.repository;

import com.bdg.domain.persistent.TripPer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface TripRepository extends JpaRepository<TripPer, Long> {


    boolean existsByCompany_Id(Long companyId);

    boolean existsByCompany_Name(String name);

    boolean existsByCompany_FoundDate(Date date);
}