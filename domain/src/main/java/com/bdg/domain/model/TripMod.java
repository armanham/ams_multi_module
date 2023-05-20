package com.bdg.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Valid
public class TripMod extends BaseMod {

    @NotNull
    private final CompanyMod company;

    @NotNull @NotEmpty @NotBlank
    private final String airplane;

    @NotNull @NotEmpty @NotBlank
    private final String townFrom;

    @NotNull @NotEmpty @NotBlank
    private final String townTo;

    @NotNull
    private final Timestamp timeOut;

    @NotNull
    private final Timestamp timeIn;

    public TripMod(
            @NotNull CompanyMod company,
            @NotNull String airplane,
            @NotNull String townFrom,
            @NotNull String townTo,
            @NotNull Timestamp timeOut,
            @NotNull Timestamp timeIn) {
        this.company = company;
        this.airplane = airplane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public @NotNull CompanyMod getCompany() {
        return company;
    }

    public @NotNull String getAirplane() {
        return airplane;
    }

    public @NotNull String getTownFrom() {
        return townFrom;
    }

    public @NotNull String getTownTo() {
        return townTo;
    }

    public @NotNull Timestamp getTimeOut() {
        return timeOut;
    }

    public @NotNull Timestamp getTimeIn() {
        return timeIn;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + getId() +
                ", company=" + company +
                ", airplane='" + airplane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                "}\n";
    }
}