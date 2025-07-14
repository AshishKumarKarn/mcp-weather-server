package com.example.mcp;

import com.example.mcp.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.spec.McpServerSession;
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
    public ToolCallbackProvider weatherToolProvider(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

}