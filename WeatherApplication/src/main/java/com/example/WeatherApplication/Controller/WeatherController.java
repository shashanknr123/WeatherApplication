package com.example.WeatherApplication.Controller;

import com.example.WeatherApplication.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public Map<String, String> getWeather(@RequestParam String lat, @RequestParam String lon) {
        try {
            return weatherService.getWeather(lat, lon);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data", e);
        }
    }
}