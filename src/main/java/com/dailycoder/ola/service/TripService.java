package com.dailycoder.ola.service;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.model.Rider;
import com.dailycoder.ola.model.Trip;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TripService {

     void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint);


     List<Trip> tripHistory(@NonNull final Rider rider);
     void endTrip(@NonNull final Cab cab);
}
