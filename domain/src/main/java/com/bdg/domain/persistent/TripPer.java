package com.bdg.domain.persistent;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(
        name = "trip",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "airplane", "town_from", "town_to", "time_out", "time_in"})}
)
@Getter
@Setter
@NoArgsConstructor
public class TripPer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, updatable = false)
    private CompanyPer company;

    @Column(name = "airplane", nullable = false, length = 24)
    private String airplane;

    @Column(name = "town_from", nullable = false, length = 64)
    private String townFrom;

    @Column(name = "town_to", nullable = false, length = 64)
    private String townTo;

    @Column(name = "time_out", nullable = false)
    private Timestamp timeOut;

    @Column(name = "time_in", nullable = false)
    private Timestamp timeIn;
}