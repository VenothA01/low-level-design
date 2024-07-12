package com.dailycoder.ola.controller;

import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.model.Rider;
import com.dailycoder.ola.model.Trip;
import com.dailycoder.ola.service.RiderService;
import com.dailycoder.ola.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class RidersController {

    private RiderService riderService;
    private TripService tripService;

    public RidersController(RiderService riderService, TripService tripService) {
        this.riderService = riderService;
        this.tripService = tripService;
    }

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String riderName) {
        riderService.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(
            final String riderId,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) {

        tripService.createTrip(
                riderService.getRider(riderId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId) {
        List<Trip> trips = tripService.tripHistory(riderService.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
