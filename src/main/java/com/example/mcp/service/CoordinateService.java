package com.example.mcp.service;

import com.example.mcp.model.FeatureCollection;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CoordinateService {


    @Tool(name = "getCoordinates", description = "Get coordinates for a given location")
    public List<Double> getCoordinates(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        WebClient webClient = WebClient.builder()
                .baseUrl("https://photon.komoot.io/api/")
                .build();

        FeatureCollection response = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lang", "en")
                        .queryParam("limit", 5)
                        .queryParam("q", location)
                        .build())
                .retrieve()
                .bodyToMono(FeatureCollection.class)
                .block();

        if (response != null && !response.features().isEmpty()) {
            return response.features().get(0).geometry().longitudeLatitudeCoordinates();
        } else {
            throw new RuntimeException("No coordinates found for the given location");
        }
    }
}
