package com.dailycoder.ola.stratergy;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Location;
import com.dailycoder.ola.model.Rider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

    @Override
    public Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint) {
        //Implement backoff strategy to retyr
        if(candidateCabs.isEmpty()){
            return null;
        }
        return candidateCabs.get(0);
    }
}
