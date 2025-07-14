package com.example.mcp.service;

import com.example.mcp.model.StockRequest;
import com.example.mcp.model.StockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    @Value("${api.ninja.key}")
    private String ninjaApiKey;


    @Tool(description = "Get the current stock price for a stock symbol", name = "CurrentStockPrice")
    public StockResponse getStockPrice(StockRequest stockRequest) {
        Boolean[] arr = new Boolean[2];
        Boolean[] booleans = Arrays.copyOf(arr, 2);
        System.out.println("Booleans array: " + Arrays.toString(booleans));
        RestClient restClient = RestClient.builder()
                .baseUrl("https://api.api-ninjas.com/v1/stockprice")
                .defaultHeader("X-Api-Key", ninjaApiKey
                )
                .build();
        log.info("Fetching stock price for ticker: {}", stockRequest.ticker());

        return restClient.get().uri("?ticker=" + stockRequest.ticker())
                .retrieve()
                .toEntity(StockResponse.class)
                .getBody();
    }
}
