package com.dailycoder.ola.service;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CabService {

    public void createCab(@NonNull final Cab cab);
    public Cab getCab(@NonNull final String cabId);
    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation);
    public void updateCabAvailability(@NonNull final String cabId, @NonNull final Boolean newAvailability);
    public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance);
}
