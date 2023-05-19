package com.bdg.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class TripMod extends BaseMod {

    @NotNull
    private CompanyMod company;

    @NotNull @NotEmpty @NotBlank
    private String airplane;

    @NotNull @NotEmpty @NotBlank
    private String townFrom;

    @NotNull @NotEmpty @NotBlank
    private String townTo;

    @NotNull
    private Timestamp timeOut;

    @NotNull
    private Timestamp timeIn;

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

    public void setCompany(final @NotNull CompanyMod companyMod) {
        this.company = companyMod;
    }

    public @NotNull String getAirplane() {
        return airplane;
    }

    public void setAirplane(final @NotNull String airplane) {
        this.airplane = airplane;
    }

    public @NotNull String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(@NotNull final String townFrom) {
        this.townFrom = townFrom;
    }

    public @NotNull String getTownTo() {
        return townTo;
    }

    public void setTownTo(@NotNull final String townTo) {
        this.townTo = townTo;
    }

    public @NotNull Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(@NotNull final Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public @NotNull Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(@NotNull final Timestamp timeIn) {
        this.timeIn = timeIn;
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