package com.arquisoft.ejercicioapi.service;

import com.arquisoft.ejercicioapi.entities.AgifyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AgifyService {
    private final RestTemplate restTemplate = new RestTemplate();

    public AgifyResponse getAgeByName(String name) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.agify.io")
                .queryParam("name", name)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, AgifyResponse.class);
    }
}