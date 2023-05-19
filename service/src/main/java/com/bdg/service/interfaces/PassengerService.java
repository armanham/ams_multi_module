package com.bdg.service.interfaces;

import com.bdg.domain.model.PassengerMod;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    PassengerMod save(PassengerMod passenger);

    Optional<PassengerMod> findById(Long id);

    Optional<PassengerMod> findByPhone(String phone);

    List<PassengerMod> findAll();

    List<PassengerMod> findAllByAddressId(Long addressId);

    List<PassengerMod> findAllByCountry(String country);

    List<PassengerMod> findAllByCity(String city);

    List<PassengerMod> findAllByCountryAndCity(String country, String city);

    List<PassengerMod> findAllByName(String name);

    PassengerMod updateById(Long id, PassengerMod newPassenger);

    boolean deleteById(Long id);

    boolean deleteByPhone(String phone);

    long deleteAllByName(String name);

    boolean existsByAddressId(Long id);
}