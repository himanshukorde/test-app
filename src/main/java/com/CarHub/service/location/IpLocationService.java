package com.CarHub.service.location;

import com.CarHub.payload.IpInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpLocationService {

    @Value("${ipinfo.api.key}")
    private String ipInfoApiKey;

    public String getLatLongFromIp(String ip) {
        String url = "https://ipinfo.io/" + ip + "?token=" + ipInfoApiKey;
        RestTemplate restTemplate = new RestTemplate();
        IpInfoResponse response = restTemplate.getForObject(url, IpInfoResponse.class);
        return response != null ? response.getLoc() : null;
    }
}
