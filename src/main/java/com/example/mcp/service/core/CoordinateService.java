package com.example.mcp.service.core;

import com.example.mcp.model.FeatureCollection;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CoordinateService {


    private static final String PHOTON_KOMOOT_IO_API = "https://photon.komoot.io/api/";

    @Tool(name = "getCoordinates", description = "Get Longitude and Latitude coordinates for a given location")
    public List<Double> getCoordinates(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        WebClient webClient = getWebClient();

        FeatureCollection response = invokeAPI(location, webClient);

        if (response != null && !response.features().isEmpty()) {
            return response.features().get(0).geometry().longitudeLatitudeCoordinates();
        } else {
            throw new RuntimeException("No coordinates found for the given location");
        }
    }


    @Tool(name = "getDetailedCoordinatesByLocation", description = "Get extended details about coordinates for a given location")
    public FeatureCollection getCoordinatesDetails(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        WebClient webClient = getWebClient();

        return invokeAPI(location, webClient);
    }

    private static WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(PHOTON_KOMOOT_IO_API)
                .build();
    }

    private static FeatureCollection invokeAPI(String location, WebClient webClient) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lang", "en")
                        .queryParam("limit", 5)
                        .queryParam("q", location)
                        .build())
                .retrieve()
                .bodyToMono(FeatureCollection.class)
                .block();
    }
}
