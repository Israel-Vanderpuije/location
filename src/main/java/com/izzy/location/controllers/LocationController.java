package com.izzy.location.controllers;

import com.izzy.location.entities.Location;
import com.izzy.location.repos.LocationRepo;
import com.izzy.location.services.LocationService;
import com.izzy.location.util.EmailUtil;
import com.izzy.location.util.ReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    ReportUtil reportUtil;

    @Autowired
    ServletContext servletContext;

    @RequestMapping("/showCreate")
    public String showCreate(){
        return "createLocation";
    }

    @RequestMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        Location locationSaved = locationService.saveLocation(location);
        String msg = "Location saved with id: " + locationSaved.getId();
        modelMap.addAttribute("msg", msg);
        emailUtil.sendEmail("cicili1800@gmail.com", "Location saved", "Location saved successfully");
        return "createLocation";
    }

    //Using ModelMap
    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap){
        List<Location> locations = locationService.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    //Using ModelAndView
//    @RequestMapping("/displayLocations")
//    public ModelAndView displayLocations(ModelAndView mav){
//        List<Location> locations = locationService.getAllLocation();
//        mav.addObject("locations", locations);
//        return mav;
//    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") Long id, ModelMap modelMap){
        locationService.deleteLocation(id);
        List<Location> locations = locationService.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") Long id, ModelMap modelMap){
        locationService.getLocationById(id).ifPresent(location -> modelMap.addAttribute("location", location));
        //modelMap.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLocation")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        locationService.updateLocation(location);
        List<Location> locations = locationService.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/generateReport")
    public String generateReport(){
        String path = servletContext.getRealPath("/");
        List<Object[]> data = locationRepo.findTypeAndTypeCount();
        reportUtil.generatePieChart(path, data);
        return "report";
    }

}

