package com.example.mcp.service;

import com.example.mcp.model.WeatherResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WeatherService {

    @Value("${api.ninja.key}")
    private String ninjaApiKey;

    @Autowired
    private CoordinateService coordinateService;

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

        // Make API call and fetch response
        // Blocking for simplicity, consider using reactive streams

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lat", latitude).queryParam("lon", longitude).build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }
}