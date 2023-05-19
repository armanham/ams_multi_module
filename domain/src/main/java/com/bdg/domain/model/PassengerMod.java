package com.bdg.domain.model;


import com.bdg.domain.persistent.PassengerPer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class PassengerMod extends BaseMod {

    private String name;
    private String phone;
    private AddressMod addressMod;

    public PassengerMod(
            final String name,
            final String phone,
            final AddressMod addressMod) {
        setName(name);
        setPhone(phone);
        setAddress(addressMod);
    }

    public PassengerMod() {
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank @NotEmpty final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(
            @NotNull @NotBlank @NotEmpty @NumberFormat(style = NumberFormat.Style.NUMBER) final String phone) {
        this.phone = phone;
    }

    public AddressMod getAddress() {
        return addressMod;
    }

    public void setAddress(@NotNull final AddressMod addressMod) {
        this.addressMod = addressMod;
    }

    public PassengerPer getPersistent() {
        PassengerPer passengerPer = new PassengerPer();
        passengerPer.setName(name);
        passengerPer.setPhone(phone);
        passengerPer.setAddress(addressMod.getPersistent());
        return passengerPer;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + addressMod +
                "}\n";
    }
}