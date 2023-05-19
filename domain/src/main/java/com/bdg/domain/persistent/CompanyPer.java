package com.bdg.domain.persistent;

import com.bdg.domain.model.CompanyMod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class CompanyPer extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 24)
    private String name;

    @Column(name = "found_date", nullable = false, updatable = false)
    private Date foundDate;

    public CompanyMod getModel() {
        CompanyMod companyMod = new CompanyMod();
        companyMod.setId(getId());
        companyMod.setName(name);
        companyMod.setFoundDate(foundDate);
        return companyMod;
    }
}