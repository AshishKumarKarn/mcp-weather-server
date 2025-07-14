package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record Feature(
        @JsonProperty("type")
        @JsonPropertyDescription("Type of the feature")
        String type,

        @JsonProperty("properties")
        @JsonPropertyDescription("Properties of the feature")
        Properties properties,

        @JsonProperty("geometry")
        @JsonPropertyDescription("Geometry of the feature")
        Geometry geometry
) {
}