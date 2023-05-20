package com.bdg.domain.model;

import com.bdg.domain.persistent.AddressPer;
import com.bdg.domain.response_json.AddressResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class AddressMod extends BaseMod {

    @NotNull @NotEmpty @NotBlank
    private final String country;

    @NotNull @NotEmpty @NotBlank
    private final String city;

    public @NotNull String getCountry() {
        return country;
    }

    public @NotNull String getCity() {
        return city;
    }

    public AddressPer getPersistent() {
        AddressPer addressPer = new AddressPer();
        addressPer.setCountry(country);
        addressPer.setCity(city);
        return addressPer;
    }

    public AddressResponse getResponse() {
        return new AddressResponse(getId(), country, city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressMod that = (AddressMod) o;
        return Objects.equals(country, that.country) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }

    @Override
    public String toString() {
        return "AddressMod{" +
                "id=" + getId() +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                "}\n";
    }
}