package com.dailycoder.ola.stratergy;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {

    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
