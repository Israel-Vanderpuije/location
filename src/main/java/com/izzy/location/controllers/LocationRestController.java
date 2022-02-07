package com.izzy.location.controllers;

import com.izzy.location.entities.Location;
import com.izzy.location.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationRestController {

    @Autowired
    LocationRepo locationRepo;

    @GetMapping
    public List<Location> getAllLocations(){
        return locationRepo.findAll();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location){
        return locationRepo.save(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location){
        return locationRepo.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable("id") Long id){
        locationRepo.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocationById(@PathVariable("id") Long id){
        return locationRepo.findById(id);
    }
}
