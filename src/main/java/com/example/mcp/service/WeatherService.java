package com.example.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {


    @Tool(name = "getWeather", description = "get weather by city name")
    public String getWeather(String city) {
        return "Weather of " + city + " is 25 degree right now.";
    }
}