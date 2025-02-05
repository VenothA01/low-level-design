package com.dailycoder.ola.controller;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.service.CabService;
import com.dailycoder.ola.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabsController {

    private CabService cabService;
    private TripService tripService;

    public CabsController(CabService cabService, TripService tripService) {
        this.cabService = cabService;
        this.tripService = tripService;
    }

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity regiserCab(final String cabId, final String driverName) {
        cabService.createCab(new Cab(cabId, driverName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/location", method = RequestMethod.POST)
    public ResponseEntity updateCabLocation(
            final String cabId, final Double newX, final Double newY) {

        cabService.updateCabLocation(cabId, new Location(newX, newY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(final String cabId, final Boolean newAvailability) {
        cabService.updateCabAvailability(cabId, newAvailability);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/end/trip", method = RequestMethod.POST)
    public ResponseEntity endTrip(final String cabId) {
        tripService.endTrip(cabService.getCab(cabId));
        return ResponseEntity.ok("");
    }
}
