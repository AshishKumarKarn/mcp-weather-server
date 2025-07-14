package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Weather response")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WeatherResponse(
    @JsonPropertyDescription("wind speed") @JsonProperty("wind_speed") double windSpeed,
    @JsonPropertyDescription("wind degrees") @JsonProperty("wind_degrees") int windDegrees,
    @JsonPropertyDescription("temperature") double temp,
    @JsonPropertyDescription("humidity percentage") int humidity,
    @JsonPropertyDescription("sunset time in epoch") long sunset,
    @JsonPropertyDescription("minimum temperature") @JsonProperty("min_temp") double minTemp,
    @JsonPropertyDescription("cloud percentage") @JsonProperty("cloud_pct") int cloudPct,
    @JsonPropertyDescription("feels like temperature") @JsonProperty("feels_like") double feelsLike,
    @JsonPropertyDescription("sunrise time in epoch") long sunrise,
    @JsonPropertyDescription("maximum temperature") @JsonProperty("max_temp") double maxTemp
) {
}
