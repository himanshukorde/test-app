package com.CarHub.controller;

import com.CarHub.service.location.IpLocationService;
import com.CarHub.service.location.ReverseGeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private IpLocationService ipLocationService;

    @Autowired
    private ReverseGeocodingService reverseGeocodingService;

    @GetMapping("/get-address/{ip}")
    public String getAddressFromIp(@PathVariable String ip) {
        // Step 1: Get lat/lng from IP
        String loc = ipLocationService.getLatLongFromIp(ip); // e.g., "12.9716,77.5946"
        if (loc == null || !loc.contains(",")) {
            return "Location not found";
        }

        String[] parts = loc.split(",");
        String lat = parts[0];
        String lng = parts[1];

        // Step 2: Get human-readable address from lat/lng (using Nominatim)
        return reverseGeocodingService.getAddressFromLatLng(lat, lng);
    }
}

