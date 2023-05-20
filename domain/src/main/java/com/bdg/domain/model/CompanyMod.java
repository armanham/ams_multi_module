package com.bdg.domain.model;

import com.bdg.domain.persistent.CompanyPer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.Objects;


public class CompanyMod extends BaseMod {

    @NotNull @NotBlank @NotEmpty
    private final String name;

    @NotNull
    private final Date foundDate;

    public CompanyMod(
            @NotNull String name,
            @NotNull Date foundDate) {
        this.name = name;
        this.foundDate = foundDate;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Date getFoundDate() {
        return foundDate;
    }

    public CompanyPer getPersistent(){
        CompanyPer companyPer = new CompanyPer();
        companyPer.setName(name);
        companyPer.setFoundDate(foundDate);
        return companyPer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyMod that = (CompanyMod) o;
        return Objects.equals(name, that.name) && Objects.equals(foundDate, that.foundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foundDate);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", foundDate=" + foundDate +
                "}\n";
    }
}