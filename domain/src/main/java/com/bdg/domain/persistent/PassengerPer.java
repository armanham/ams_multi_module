package com.bdg.domain.persistent;

import com.bdg.domain.model.PassengerMod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
public class PassengerPer extends BaseEntity {

    @Column(name = "name", nullable = false, length = 24)
    private String name;

    @Column(name = "phone", nullable = false, unique = true, length = 24)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressPer address;

    public PassengerMod getModel() {
        PassengerMod passengerMod = new PassengerMod(name, phone, address.getModel());
        passengerMod.setId(getId());
        return passengerMod;
    }
}