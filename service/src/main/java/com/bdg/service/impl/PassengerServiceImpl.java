package com.bdg.service.impl;

import com.bdg.domain.model.PassengerMod;
import com.bdg.domain.persistent.AddressPer;
import com.bdg.domain.persistent.PassengerPer;
import com.bdg.exception.WrongIdException;
import com.bdg.repository.AddressRepository;
import com.bdg.repository.PassInTripRepository;
import com.bdg.repository.PassengerRepository;
import com.bdg.service.interfaces.PassengerService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final AddressRepository addressRepository;
    private final PassInTripRepository passInTripRepository;

    @Autowired
    public PassengerServiceImpl(
            PassengerRepository passengerRepository,
            AddressRepository addressRepository,
            PassInTripRepository passInTripRepository) {
        this.passengerRepository = passengerRepository;
        this.addressRepository = addressRepository;
        this.passInTripRepository = passInTripRepository;
    }


    @Override
    public PassengerMod save(@NotNull PassengerMod passenger) {
        if (passengerRepository.existsByPhone(passenger.getPhone())) {
            return null;
        }

        Optional<AddressPer> optionalAddressPer =
                addressRepository.findByCountryAndCity(passenger.getAddress().getCountry(), passenger.getAddress().getCity());

        PassengerPer passengertoSave = passenger.getPersistent();

        if (optionalAddressPer.isPresent()) {
            passengertoSave.setAddress(optionalAddressPer.get());
            passenger.getAddress().setId(optionalAddressPer.get().getId());
        } else {
            AddressPer newSavedAddress = addressRepository.save(passenger.getAddress().getPersistent());
            passengertoSave.setAddress(newSavedAddress);
            passenger.getAddress().setId(newSavedAddress.getId());
        }

        passengerRepository.save(passengertoSave);
        passenger.getAddress().setId(passengertoSave.getAddress().getId());
        passenger.setId(passengertoSave.getId());
        return passenger;
    }


    @Override
    public Optional<PassengerMod> findById(Long id) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }
        return passengerRepository.findById(id).map(PassengerPer::getModel);
    }


    @Override
    public Optional<PassengerMod> findByPhone(
            @NotNull @NotBlank @NotEmpty @NumberFormat(style = NumberFormat.Style.NUMBER) String phone) {
        return passengerRepository.findByPhone(phone).map(PassengerPer::getModel);
    }


    @Override
    public List<PassengerMod> findAll() {
        return passengerRepository.findAll()
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public List<PassengerMod> findAllByAddressId(Long addressId) {
        if (addressId <= 0) {
            throw new WrongIdException(addressId);
        }
        return passengerRepository.findAllByAddress_Id(addressId)
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public List<PassengerMod> findAllByCountry(@NotNull @NotBlank @NotEmpty String country) {
        return passengerRepository.findAllByAddress_Country(country)
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public List<PassengerMod> findAllByCity(@NotNull @NotBlank @NotEmpty String city) {
        return passengerRepository.findAllByAddress_City(city)
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public List<PassengerMod> findAllByCountryAndCity(
            @NotNull @NotBlank @NotEmpty String country,
            @NotNull @NotBlank @NotEmpty String city) {

        return passengerRepository.findAllByAddress_CountryAndAddress_City(country, city)
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public List<PassengerMod> findAllByName(@NotNull @NotBlank @NotEmpty String name) {
        return passengerRepository.findAllByName(name)
                .stream()
                .map(PassengerPer::getModel)
                .toList();
    }


    @Override
    public PassengerMod updateById(Long id, @NotNull PassengerMod newPassenger) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }

        Optional<PassengerPer> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isEmpty()) {
            return null;
        }

        if (passengerRepository.existsByPhone(newPassenger.getPhone())) {
            return null;
        }

        Optional<AddressPer> optionalAddressPer =
                addressRepository.findByCountryAndCity(newPassenger.getAddress().getCountry(), newPassenger.getAddress().getCity());

        PassengerPer passengerToUpdate = optionalPassenger.get();
        passengerToUpdate.setName(newPassenger.getName());
        passengerToUpdate.setPhone(newPassenger.getPhone());

        if (optionalAddressPer.isPresent()) {
            passengerToUpdate.setAddress(optionalAddressPer.get());
            newPassenger.getAddress().setId(optionalAddressPer.get().getId());
        } else {
            AddressPer newSavedAddress = addressRepository.save(newPassenger.getAddress().getPersistent());
            passengerToUpdate.setAddress(newSavedAddress);
            newPassenger.getAddress().setId(newSavedAddress.getId());
        }

        passengerRepository.save(passengerToUpdate);
        newPassenger.getAddress().setId(passengerToUpdate.getAddress().getId());
        newPassenger.setId(passengerToUpdate.getId());
        return newPassenger;
    }


    @Override
    public boolean deleteById(Long id) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }

        if (passInTripRepository.existsByPassenger_Id(id)) {
            return false;
        }

        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteByPhone(
            @NotNull @NotBlank @NotEmpty @NumberFormat(style = NumberFormat.Style.NUMBER) String phone) {
        if (passInTripRepository.existsByPassenger_Phone(phone)) {
            return false;
        }

        if (passengerRepository.existsByPhone(phone)) {
            passengerRepository.deleteByPhone(phone);
            return true;
        }
        return false;
    }


    @Override
    public long deleteAllByName(@NotNull @NotBlank @NotEmpty String name) {
        if (passInTripRepository.existsByPassenger_Name(name)) {
            return 0;
        }

        return passengerRepository.deleteAllByName(name);
    }


    @Override
    public boolean existsByAddressId(Long addressId) {
        if (addressId <= 0) {
            throw new WrongIdException(addressId);
        }
        return passengerRepository.existsByAddress_Id(addressId);
    }
}