package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

public record Properties(
        @JsonProperty("osm_type")
        @JsonPropertyDescription("OSM type")
        String osmType,

        @JsonProperty("osm_id")
        @JsonPropertyDescription("OSM ID")
        long osmId,

        @JsonProperty("osm_key")
        @JsonPropertyDescription("OSM key")
        String osmKey,

        @JsonProperty("osm_value")
        @JsonPropertyDescription("OSM value")
        String osmValue,

        @JsonProperty("type")
        @JsonPropertyDescription("Type of the property")
        String type,

        @JsonProperty("postcode")
        @JsonPropertyDescription("Postcode")
        String postcode,

        @JsonProperty("countrycode")
        @JsonPropertyDescription("Country code")
        String countryCode,

        @JsonProperty("name")
        @JsonPropertyDescription("Name of the place")
        String name,

        @JsonProperty("country")
        @JsonPropertyDescription("Country")
        String country,

        @JsonProperty("city")
        @JsonPropertyDescription("City")
        String city,

        @JsonProperty("district")
        @JsonPropertyDescription("District")
        String district,

        @JsonProperty("locality")
        @JsonPropertyDescription("Locality")
        String locality,

        @JsonProperty("street")
        @JsonPropertyDescription("Street")
        String street,

        @JsonProperty("state")
        @JsonPropertyDescription("State")
        String state,

        @JsonProperty("county")
        @JsonPropertyDescription("County")
        String county,

        @JsonProperty("extent")
        @JsonPropertyDescription("Extent coordinates")
        List<Double> extent
) {
}