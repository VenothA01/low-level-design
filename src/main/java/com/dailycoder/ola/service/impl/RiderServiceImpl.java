package com.dailycoder.ola.service.impl;

import com.dailycoder.ola.exceptions.RiderAlreadyExistsException;
import com.dailycoder.ola.exceptions.RiderNotFoundException;
import com.dailycoder.ola.model.Rider;
import com.dailycoder.ola.service.RiderService;
import com.dailycoder.ola.store.InMemoryDataStore;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RiderServiceImpl implements RiderService {

    private InMemoryDataStore inMemoryDataStore;

    @Autowired
    public RiderServiceImpl(InMemoryDataStore inMemoryDataStore){
        this.inMemoryDataStore=inMemoryDataStore;
    }

    @Override
    public void createRider(@NonNull Rider newRider) {
        if(inMemoryDataStore.riders.containsKey(newRider.getId())){
            throw  new RiderAlreadyExistsException();
        }
        inMemoryDataStore.riders.put(newRider.getId(),newRider);
    }

    @Override
    public Rider getRider(@NonNull String riderId) {
        if (!inMemoryDataStore.riders.containsKey(riderId)) {
            throw new RiderNotFoundException();
        }
        return inMemoryDataStore.riders.get(riderId);
    }
}
