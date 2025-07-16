package com.example.mcp.service;

import com.example.mcp.model.WeatherResponse;
import com.example.mcp.service.core.CoordinateService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WeatherService {

    private final String ninjaApiKey;
    private final CoordinateService coordinateService;

    public WeatherService(@Value("${api.ninja.key}") String ninjaApiKey, CoordinateService coordinateService) {
        this.ninjaApiKey = ninjaApiKey;
        this.coordinateService = coordinateService;
    }

    /**
     * This service provides methods to get the current weather for a given city.
     * It uses the Ninjas Weather API to fetch the weather data.
     */
    @SuppressWarnings("unused")
    @Tool(name = "getWeather", description = "get weather by city name")
    public WeatherResponse getWeather(String city) {
        List<Double> coordinates = coordinateService.getCoordinates(city);
        Double longitude = coordinates.get(0);
        Double latitude = coordinates.get(1);

        // Create WebClient instance
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.api-ninjas.com/v1/weather/")
                .defaultHeader("X-Api-Key", ninjaApiKey) // Replace with actual API key
                .build();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lat", latitude).queryParam("lon", longitude).build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }
}