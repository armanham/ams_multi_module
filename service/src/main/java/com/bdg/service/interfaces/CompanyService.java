package com.bdg.service.interfaces;

import com.bdg.domain.model.CompanyMod;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyMod save(CompanyMod company);

    Optional<CompanyMod> findById(Long id);

    Optional<CompanyMod> findByName(String name);

    List<CompanyMod> findAllByFoundDate(Date date);

    CompanyMod updateById(Long id, String newName);

    boolean deleteById(Long id);

    boolean deleteByName(String name);

    int deleteAllByFoundDate(Date date);
}