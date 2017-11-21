package com.ibm.actrec;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "location", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"last_location_time", "person"})
})
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "last_location_time", nullable = false)
    private String lastLocationTime;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "person", nullable = false)
    private Person person;

    public Location(String lastLocationTime, double latitude, double longitude, Person person) {
        this.lastLocationTime = lastLocationTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.person = person;
    }

    Location() { } // jpa only
}
