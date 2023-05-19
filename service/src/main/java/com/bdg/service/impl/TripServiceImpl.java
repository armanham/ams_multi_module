package com.bdg.service.impl;

import com.bdg.domain.model.TripMod;
import com.bdg.repository.TripRepository;
import com.bdg.service.interfaces.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


//    @Override
//    public TripMod save(@NotNull TripMod trip) {
//        TripPer tripPer = modToPerTrip.getPersistentFrom(trip);
//
//        if (tripRepository.exists(tripPer)){
//            return null;
//        }
//
//
//        return null;
//    }


    @Override
    public TripMod save(TripMod trip) {
        return null;
    }
}