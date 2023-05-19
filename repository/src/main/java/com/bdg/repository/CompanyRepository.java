package com.bdg.repository;

import com.bdg.domain.persistent.CompanyPer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyPer, Long> {

    Optional<CompanyPer> findByName(String name);

    List<CompanyPer> findAllByFoundDate(Date date);

    void deleteByName(String name);

    int deleteAllByFoundDate(Date foundDate);

    boolean existsByName(String name);
}