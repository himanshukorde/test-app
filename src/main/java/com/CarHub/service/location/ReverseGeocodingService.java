package com.CarHub.service.location;

import com.CarHub.payload.NominatimResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ReverseGeocodingService {

    public String getAddressFromLatLng(String lat, String lon) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("format", "json")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("addressdetails", 1)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        try {
            NominatimResponse response = restTemplate.getForObject(url, NominatimResponse.class);
            return response != null ? response.getDisplay_name() : "Address not found";
        } catch (Exception e) {
            return "Error fetching address: " + e.getMessage();
        }
    }
}
