package com.bdg.domain.model;

import com.bdg.domain.persistent.AddressPer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


public class AddressMod extends BaseMod {

    private String country;

    private String city;

    public AddressMod(final String country, final String city) {
        setCountry(country);
        setCity(city);
    }

    public AddressMod() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(@NotNull @NotBlank @NotEmpty final String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(@NotNull @NotBlank @NotEmpty final String city) {
        this.city = city;
    }

    public AddressPer getPersistent(){
        AddressPer addressPer = new AddressPer();
        addressPer.setCountry(country);
        addressPer.setCity(city);
        return addressPer;
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