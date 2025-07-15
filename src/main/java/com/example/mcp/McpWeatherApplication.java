package com.example.mcp;

import com.example.mcp.service.StockService;
import com.example.mcp.service.WeatherService;
import com.example.mcp.service.core.CoordinateService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpWeatherApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherToolProvider(WeatherService weatherService, StockService stockService, CoordinateService coordinateService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService, stockService, coordinateService).build();
    }

}