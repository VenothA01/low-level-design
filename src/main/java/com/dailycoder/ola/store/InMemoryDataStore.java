package com.dailycoder.ola.store;

import com.dailycoder.ola.model.Cab;
import com.dailycoder.ola.model.Rider;
import com.dailycoder.ola.model.Trip;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component

public class InMemoryDataStore {

    public Map<String, Cab> cabs = new HashMap<>();
    public Map<String, Rider> riders = new HashMap<>();
    public Map<String, List<Trip>> trips = new HashMap<>();
}
