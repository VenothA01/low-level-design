package com.dailycoder.ola.stratergy;

import com.dailycoder.ola.model.Location;

public interface PricingStrategy {

    Double findPrice(Location fromPoint, Location toPoint);

}
