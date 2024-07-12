package com.dailycoder.ola.model;

import lombok.NonNull;

import static com.dailycoder.ola.model.TripStatus.*;

public class Trip {

    private Rider rider;
    private Cab cab;
    private TripStatus status;
    private Double price;
    private Location fromPoint;
    private Location toPoint;

    public Trip(@NonNull final Rider rider,
                @NonNull final Cab cab,
                @NonNull final Double price,
                @NonNull final Location fromPoint,
                @NonNull final Location toPoint){
        this.rider = rider;
        this.price = price;
        this.cab = cab;
        this.status = IN_PROGRESS;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }

    public void endTrip() {
        this.status = FINISHED;
    }

}
