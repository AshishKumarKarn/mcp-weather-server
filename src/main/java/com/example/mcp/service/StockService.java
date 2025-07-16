package com.example.mcp.service;

import com.example.mcp.model.StockRequest;
import com.example.mcp.model.StockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private static final String API_NINJAS_STOCK_PRICE = "https://api.api-ninjas.com/v1/stockprice";

    private final String ninjaApiKey;

    public StockService(@Value("${api.ninja.key}") String ninjaApiKey) {
        this.ninjaApiKey = ninjaApiKey;
    }


    @Tool(description = "Get the current stock price for a stock symbol", name = "CurrentStockPrice")
    public StockResponse getStockPrice(StockRequest stockRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(API_NINJAS_STOCK_PRICE)
                .defaultHeader("X-Api-Key", ninjaApiKey)
                .build();
        log.info("Fetching stock price for ticker: {}", stockRequest.ticker());

        return restClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("ticker", stockRequest.ticker()).build())
                .retrieve()
                .body(StockResponse.class);
    }
}
