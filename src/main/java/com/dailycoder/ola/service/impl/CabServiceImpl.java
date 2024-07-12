package com.dailycoder.ola.service.impl;

import com.dailycoder.ola.exceptions.CabAlreadyExistsException;
import com.dailycoder.ola.exceptions.CabNotFoundException;
import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.service.CabService;
import com.dailycoder.ola.store.InMemoryDataStore;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CabServiceImpl implements CabService {


    private InMemoryDataStore inMemoryDataStore;

    @Autowired
    public CabServiceImpl(InMemoryDataStore inMemoryDataStore) {
        this.inMemoryDataStore = inMemoryDataStore;
    }

    @Override
    public void createCab(@NonNull Cab newCab) {
        if(inMemoryDataStore.cabs.containsKey(newCab.getId())){
            throw new CabAlreadyExistsException();
        }
        inMemoryDataStore.cabs.put(newCab.getId(),newCab);
    }

    @Override
    public Cab getCab(@NonNull String cabId) {
        if(!inMemoryDataStore.cabs.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        return inMemoryDataStore.cabs.get(cabId);
    }

    @Override
    public void updateCabLocation(@NonNull String cabId, @NonNull Location newLocation) {
        if (!inMemoryDataStore.cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        Cab updatedCab = inMemoryDataStore.cabs.get(cabId);
        updatedCab.setCurrentLocation(newLocation);
        inMemoryDataStore.cabs.put(cabId,updatedCab);
    }

    @Override
    public void updateCabAvailability(@NonNull String cabId, @NonNull Boolean newAvailability) {
        if (!inMemoryDataStore.cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        Cab updatedCab = inMemoryDataStore.cabs.get(cabId);
        updatedCab.setIsAvailable(newAvailability);
        inMemoryDataStore.cabs.put(cabId,updatedCab);
    }

    public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance) {
        List<Cab> result = new ArrayList<>();
        for (Cab cab : inMemoryDataStore.cabs.values()) {
            if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
                result.add(cab);
            }
        }
        return result;
    }
}
