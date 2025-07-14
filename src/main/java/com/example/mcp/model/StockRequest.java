package com.example.mcp.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Stock price request")
public record StockRequest(@JsonPropertyDescription("ticker name of the stock to quote")String ticker) {

    public StockRequest {
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Ticker cannot be null or blank");
        }
    }
}
