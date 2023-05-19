package com.bdg.service.interfaces;

import com.bdg.domain.model.AddressMod;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    AddressMod save(AddressMod address);

    Optional<AddressMod> findById(Long id);

    List<AddressMod> findAll();

    List<AddressMod> findAllByCity(String city);

    List<AddressMod> findAllByCountry(String country);

    AddressMod updateById(Long id, AddressMod newAddress);

    boolean deleteById(Long id);

    long deleteAllByCountry(String country);

    long deleteAllByCity(String city);

}