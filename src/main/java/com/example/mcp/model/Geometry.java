package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

public record Geometry(
        @JsonProperty("type")
        @JsonPropertyDescription("Type of the geometry")
        String type,

        @JsonProperty("coordinates")
        @JsonPropertyDescription("Coordinates of the geometry")
        List<Double> longitudeLatitudeCoordinates
) {
}