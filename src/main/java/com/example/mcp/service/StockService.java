package com.example.mcp.service;

import com.example.mcp.model.StockRequest;
import com.example.mcp.model.StockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    @Value("${api.ninja.key}")
    private String ninjaApiKey;


    @Tool(description = "Get the current stock price for a stock symbol", name = "CurrentStockPrice")
    public StockResponse getStockPrice(StockRequest stockRequest) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.api-ninjas.com/v1/stockprice")
                .defaultHeader("X-Api-Key", ninjaApiKey)
                .build();

        log.info("Fetching stock price for ticker: {}", stockRequest.ticker());

        Mono<StockResponse> ticker = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("ticker", stockRequest.ticker()).build())
                .retrieve()
                .bodyToMono(StockResponse.class);
        return ticker.block();
    }
}
