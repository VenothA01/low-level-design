package com.dailycoder.ola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter

public class Cab {

    String id;
    String driverName;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
    }

    @Setter Trip currentTrip;
    @Setter Location currentLocation;
    @Setter Boolean isAvailable;


}
