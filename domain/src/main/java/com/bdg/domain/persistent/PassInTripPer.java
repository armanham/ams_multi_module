package com.bdg.domain.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(
        name = "pass_in_trip",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"trip_id", "passenger_id", "place"})}
)
@Getter
@Setter
@NoArgsConstructor
public class PassInTripPer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false)
    private TripPer trip;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private PassengerPer passenger;

    @Column(name = "time", updatable = false, nullable = false)
    private Timestamp time;

    @Column(name = "place", updatable = false, nullable = false, length = 3)
    private String place;
}