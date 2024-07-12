package com.dailycoder.ola.service;

import com.dailycoder.ola.model.Rider;
import lombok.NonNull;

public interface RiderService {

    public void createRider(@NonNull final Rider newRider);
    public Rider getRider(@NonNull final String riderId);
}
