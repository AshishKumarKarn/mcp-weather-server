package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

public record FeatureCollection(
        @JsonProperty("type")
        @JsonPropertyDescription("Type of the feature collection")
        String type,

        @JsonProperty("features")
        @JsonPropertyDescription("List of features in the collection")
        List<Feature> features
) {
}