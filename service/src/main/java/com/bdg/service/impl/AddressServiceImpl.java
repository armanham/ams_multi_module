package com.bdg.service.impl;

import com.bdg.domain.model.AddressMod;
import com.bdg.domain.persistent.AddressPer;
import com.bdg.exception.WrongIdException;
import com.bdg.repository.AddressRepository;
import com.bdg.repository.PassengerRepository;
import com.bdg.service.interfaces.AddressService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public AddressServiceImpl(
            AddressRepository addressRepository,
            PassengerRepository passengerRepository) {
        this.addressRepository = addressRepository;
        this.passengerRepository = passengerRepository;
    }


    @Override
    public AddressMod save(@NotNull AddressMod address) {
        if (addressRepository.existsByCountryAndCity(address.getCountry(), address.getCity())) {
            return null;
        }

        AddressPer addressToSave = addressRepository.save(address.getPersistent());
        address.setId(addressToSave.getId());
        return address;
    }


    @Override
    public Optional<AddressMod> findById(Long id) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }
        return addressRepository.findById(id).map(AddressPer::getModel);
    }


    @Override
    public List<AddressMod> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressPer::getModel)
                .toList();
    }


    @Override
    public List<AddressMod> findAllByCity(@NotNull @NotBlank @NotEmpty String city) {
        return addressRepository.findAllByCity(city)
                .stream()
                .map(AddressPer::getModel)
                .toList();
    }


    @Override
    public List<AddressMod> findAllByCountry(@NotNull @NotBlank @NotEmpty String country) {
        return addressRepository.findAllByCountry(country)
                .stream()
                .map(AddressPer::getModel)
                .toList();
    }


    @Override
    public AddressMod updateById(Long id, @NotNull AddressMod newAddress) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }

        Optional<AddressPer> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            System.out.println("Address with " + id + " does not exists: ");
            return null;
        }

        Optional<AddressPer> newAddressOptional = addressRepository.findByCountryAndCity(newAddress.getCountry(), newAddress.getCity());
        if (newAddressOptional.isPresent()) {
            System.out.println(newAddress + " already exists: ");
            return null;
        }

        AddressPer addressToUpdate = optionalAddress.get();
        addressToUpdate.setCountry(newAddress.getCountry());
        addressToUpdate.setCity(newAddress.getCity());

        addressRepository.save(addressToUpdate);

        return newAddress;
    }


    @Override
    public boolean deleteById(Long id) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }

        if (passengerRepository.existsByAddress_Id(id)) {
            return false;
        }

        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public long deleteAllByCountry(@NotNull @NotBlank @NotEmpty String country) {
        if (passengerRepository.existsByAddress_Country(country)) {
            return 0;
        }

        return addressRepository.deleteAllByCountry(country);
    }


    @Override
    public long deleteAllByCity(@NotNull @NotBlank @NotEmpty String city) {
        if (passengerRepository.existsByAddress_City(city)) {
            return 0;
        }

        return addressRepository.deleteAllByCity(city);
    }
}