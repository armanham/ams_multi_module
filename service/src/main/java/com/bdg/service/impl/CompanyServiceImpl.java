package com.bdg.service.impl;

import com.bdg.domain.model.CompanyMod;
import com.bdg.domain.persistent.CompanyPer;
import com.bdg.exception.WrongIdException;
import com.bdg.repository.CompanyRepository;
import com.bdg.repository.TripRepository;
import com.bdg.service.interfaces.CompanyService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final TripRepository tripRepository;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            TripRepository tripRepository) {
        this.companyRepository = companyRepository;
        this.tripRepository = tripRepository;
    }


    public CompanyMod save(@NotNull CompanyMod company) {
        if (companyRepository.existsByName(company.getName())) {
            return null;
        }

        CompanyPer savedCompany = companyRepository.save(company.getPersistent());
        company.setId(savedCompany.getId());

        return company;
    }


    @Override
    public Optional<CompanyMod> findById(Long id) {
        if (id < 0) {
            throw new WrongIdException(id);
        }
        return companyRepository
                .findById(id)
                .map(CompanyPer::getModel);
    }


    @Override
    public Optional<CompanyMod> findByName(@NotNull @NotEmpty @NotBlank String name) {
        return companyRepository
                .findByName(name)
                .map(CompanyPer::getModel);
    }


    @Override
    public List<CompanyMod> findAllByFoundDate(@NotNull Date date) {
        return companyRepository
                .findAllByFoundDate(date)
                .stream()
                .map(CompanyPer::getModel)
                .toList();
    }


    @Override
    public CompanyMod updateById(Long id, @NotNull @NotEmpty @NotBlank String newName) {
        if (id <= 0){
            throw new WrongIdException(id);
        }

        Optional<CompanyPer> optionalCompanyPer = companyRepository.findById(id);
        if (optionalCompanyPer.isEmpty()) {
            return null;
        }

        if (companyRepository.existsByName(newName)) {
            return null;
        }

        CompanyPer companyToUpdate = optionalCompanyPer.get();
        companyToUpdate.setName(newName);
        companyToUpdate.setFoundDate(Date.valueOf(LocalDate.now()));

        return companyRepository.save(companyToUpdate).getModel();
    }


    @Override
    public boolean deleteById(Long id) {
        if (id <= 0){
            throw new WrongIdException(id);
        }

        if (tripRepository.existsByCompany_Id(id)) {
            return false;
        }

        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteByName(@NotNull @NotEmpty @NotBlank String name) {
        if (tripRepository.existsByCompany_Name(name)) {
            return false;
        }

        if (tripRepository.existsByCompany_Name(name)) {
            companyRepository.deleteByName(name);
            return true;
        }
        return false;
    }


    @Override
    public int deleteAllByFoundDate(@NotNull Date date) {
        if (tripRepository.existsByCompany_FoundDate(date)) {
            return 0;
        }

        return companyRepository.deleteAllByFoundDate(date);
    }
}