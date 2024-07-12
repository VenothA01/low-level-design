package com.dailycoder.ola.service.impl;

import com.dailycoder.ola.exceptions.NoCabsAvailableException;
import com.dailycoder.ola.exceptions.TripNotFoundException;
import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.model.Rider;
import com.dailycoder.ola.model.Trip;
import com.dailycoder.ola.service.CabService;
import com.dailycoder.ola.service.RiderService;
import com.dailycoder.ola.service.TripService;
import com.dailycoder.ola.store.InMemoryDataStore;
import com.dailycoder.ola.stratergy.CabMatchingStrategy;
import com.dailycoder.ola.stratergy.PricingStrategy;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {


    private CabService cabService;
    private RiderService riderService;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;

    private InMemoryDataStore inMemoryDataStore;

    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;

    public TripServiceImpl(CabService cabService, RiderService riderService, CabMatchingStrategy cabMatchingStrategy, PricingStrategy pricingStrategy,InMemoryDataStore inMemoryDataStore) {
        this.cabService = cabService;
        this.riderService = riderService;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
        this.inMemoryDataStore = inMemoryDataStore;
    }

    @Override
    public void createTrip(@NonNull Rider rider, @NonNull Location fromPoint, @NonNull Location toPoint) {
        final List<Cab> closeByCabs =
                cabService.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        final List<Cab> closeByAvailableCabs =
                closeByCabs.stream()
                        .filter(cab -> cab.getCurrentTrip() == null)
                        .collect(Collectors.toList());

        final Cab selectedCab =
                cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
        if (selectedCab == null) {
            throw new NoCabsAvailableException();
        }
        final Double price = pricingStrategy.findPrice(fromPoint, toPoint);
        final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);
        if (!inMemoryDataStore.trips.containsKey(rider.getId())) {
            inMemoryDataStore.trips.put(rider.getId(), new ArrayList<>());
        }
        inMemoryDataStore.trips.get(rider.getId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    @Override
    public List<Trip> tripHistory(@NonNull Rider rider) {
        return inMemoryDataStore.trips.get(rider.getId());
    }

    @Override
    public void endTrip(@NonNull Cab cab) {
        if (cab.getCurrentTrip() == null) {
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
