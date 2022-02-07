package com.izzy.location.services;

import com.izzy.location.entities.Location;
import com.izzy.location.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location saveLocation(Location location){
        return locationRepo.save(location);
    }

    public Location updateLocation(Location location){
        return locationRepo.save(location);
    }

    public void deleteLocation(Long id){
        locationRepo.deleteById(id);

    }

    public Optional<Location> getLocationById(Long id){
        return locationRepo.findById(id);
    }

    public List<Location> getAllLocation(){
        return locationRepo.findAll();
    }


}
