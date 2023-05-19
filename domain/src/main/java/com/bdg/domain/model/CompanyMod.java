package com.bdg.domain.model;

import com.bdg.domain.persistent.CompanyPer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.Objects;


public class CompanyMod extends BaseMod {

    private String name;
    private Date foundDate;

    public CompanyMod(final String name, final Date foundDate) {
        setName(name);
        setFoundDate(foundDate);
    }

    public CompanyMod() {
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank @NotEmpty final String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(@NotNull final Date foundDate) {
        this.foundDate = foundDate;
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