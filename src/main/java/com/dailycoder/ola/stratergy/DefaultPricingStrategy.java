package com.dailycoder.ola.stratergy;

import com.dailycoder.ola.model.Location;
import org.springframework.stereotype.Component;

@Component
public class DefaultPricingStrategy implements PricingStrategy {

    public static final Double PER_KM_RATE = 10.0;

    @Override
    public Double findPrice(Location fromPoint, Location toPoint) {
        return fromPoint.distance(toPoint) * PER_KM_RATE;
    }
}
