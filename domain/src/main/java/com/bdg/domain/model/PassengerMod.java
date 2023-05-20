package com.bdg.domain.model;


import com.bdg.domain.persistent.PassengerPer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class PassengerMod extends BaseMod {

    @NotNull @NotBlank @NotEmpty
    private final String name;

    @NotNull @NotBlank @NotEmpty @NumberFormat(style = NumberFormat.Style.NUMBER)
    private final String phone;

    @NotNull @Valid
    private final AddressMod addressMod;

    public PassengerMod(
            @NotNull String name,
            @NotNull String phone,
            @NotNull @Valid AddressMod addressMod) {
        this.name = name;
        this.phone = phone;
        this.addressMod = addressMod;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getPhone() {
        return phone;
    }

    public @NotNull AddressMod getAddress() {
        return addressMod;
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