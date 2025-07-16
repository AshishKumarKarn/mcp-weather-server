package com.example.mcp.service.core;

import com.example.mcp.model.FeatureCollection;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CoordinateService {


    private static final String PHOTON_KOMOOT_IO_API = "https://photon.komoot.io/api/";

    /**
     * This service provides methods to get coordinates for a given location.
     * It uses the Photon API to fetch longitude and latitude information.
     */
    @SuppressWarnings("unused")
    @Tool(name = "getCoordinates", description = "Get Longitude and Latitude coordinates for a given location")
    public List<Double> getCoordinates(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        RestClient restClient = getRestClient();

        FeatureCollection response = invokeAPI(location, restClient);

        if (response != null && !response.features().isEmpty()) {
            return response.features().getFirst().geometry().longitudeLatitudeCoordinates();
        } else {
            throw new RuntimeException("No coordinates found for the given location");
        }
    }


    /**
     * This service provides methods to get detailed coordinates for a given location.
     * It uses the Photon API to fetch extended information about the coordinates.
     */
    @SuppressWarnings("unused")
    @Tool(name = "getDetailedCoordinatesByLocation", description = "Get extended and detailed information about coordinates for a given location")
    public FeatureCollection getCoordinatesDetails(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        RestClient restClient = getRestClient();

        return invokeAPI(location, restClient);
    }

    private static RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(PHOTON_KOMOOT_IO_API)
                .build();
    }

    private static FeatureCollection invokeAPI(String location, RestClient restClient) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lang", "en")
                        .queryParam("limit", 5)
                        .queryParam("q", location)
                        .build())
                .retrieve()
                .body(FeatureCollection.class);
    }
}
