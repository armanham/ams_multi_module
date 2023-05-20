package com.bdg.controller;

import com.bdg.domain.model.AddressMod;
import com.bdg.domain.request_json.AddressRequest;
import com.bdg.domain.response_json.AddressResponse;
import com.bdg.exception.AlreadyExistsException;
import com.bdg.service.interfaces.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/address")
@Validated
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping(value = "/new")
    public ResponseEntity<?> add(@Valid @RequestBody AddressRequest addressRequest) {
        try {
            AddressResponse response = addressService.save(addressRequest.getModel()).getResponse();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list/byId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") @Min(value = 1) Long id) {
        return new ResponseEntity<>(addressService.findById(id).getResponse(), HttpStatus.OK);
    }


    @GetMapping(value = "/list")
    public List<AddressMod> getAll() {
        return addressService.findAll();
    }


    @GetMapping(value = "/list/byCountry/{country}")
    public List<AddressMod> getAllByCountry(@PathVariable("country") String country) {
        return addressService.findAllByCountry(country);
    }


    @GetMapping(value = "/list/byCity/{city}")
    public List<AddressMod> getAllByCity(@PathVariable("city") String city) {
        return addressService.findAllByCity(city);
    }


    @PutMapping(value = "/update/{id}")
    public AddressMod updateBy(@PathVariable("id") Long id, @RequestBody AddressMod newAddress) {
        return addressService.updateById(id, newAddress);
    }



    @DeleteMapping(value = "/delete/byId/{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        return addressService.deleteById(id);
    }


    @DeleteMapping(value = "/delete/byCountry/{country}")
    public long deleteAllByCountry(@PathVariable("country") String country) {
        return addressService.deleteAllByCountry(country);
    }


    @DeleteMapping(value = "/delete/byCity/{city}")
    public long deleteAllByCity(@PathVariable("city") String city) {
        return addressService.deleteAllByCity(city);
    }
}