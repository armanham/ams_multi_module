package com.bdg.domain.persistent;

import com.bdg.domain.model.AddressMod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "address",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "city"})}
)
@Getter
@Setter
@NoArgsConstructor
public class AddressPer extends BaseEntity {

    @Column(name = "country", nullable = false, length = 64)
    private String country;

    @Column(name = "city", nullable = false, length = 64)
    private String city;

    public AddressMod getModel() {
        AddressMod addressMod = new AddressMod();
        addressMod.setId(getId());
        addressMod.setCountry(country);
        addressMod.setCity(city);
        return addressMod;
    }
}