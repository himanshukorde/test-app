package com.CarHub.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// VINService.java
@Service
public class VINService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> decodeVin(String vin) {
        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVin/" + vin + "?format=json";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("Results");
        Map<String, String> vehicleInfo = new HashMap<>();

        for (Map<String, Object> result : results) {
            String label = (String) result.get("Variable");
            String value = (String) result.get("Value");

            if ("Make".equalsIgnoreCase(label)) vehicleInfo.put("brand", value);
            if ("Model".equalsIgnoreCase(label)) vehicleInfo.put("model", value);
            if ("Fuel Type - Primary".equalsIgnoreCase(label)) vehicleInfo.put("fuelType", value);
            if ("Transmission Style".equalsIgnoreCase(label)) vehicleInfo.put("transmission", value);
            if ("Model Year".equalsIgnoreCase(label)) vehicleInfo.put("year", value);
        }

        return vehicleInfo;
    }
}

