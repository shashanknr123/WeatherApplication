package com.example.WeatherApplication.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.hc.client5.http.fluent.Request;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    public Map<String, String> getWeather(String lat, String lon) throws Exception {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric", lat, lon, apiKey);

        String response = Request.get(url).execute().returnContent().asString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);

        Map<String, String> weatherData = new HashMap<>();

        weatherData.put("temperature", node.get("main").get("temp").asText());
        weatherData.put("feelsLike", node.get("main").get("feels_like").asText());
        weatherData.put("tempMin", node.get("main").get("temp_min").asText());
        weatherData.put("tempMax", node.get("main").get("temp_max").asText());
        weatherData.put("pressure", node.get("main").get("pressure").asText());
        weatherData.put("humidity", node.get("main").get("humidity").asText());
        weatherData.put("weatherMain", node.get("weather").get(0).get("main").asText());
        weatherData.put("weatherDescription", node.get("weather").get(0).get("description").asText());
        weatherData.put("weatherIcon", node.get("weather").get(0).get("icon").asText());
        weatherData.put("windSpeed", node.get("wind").get("speed").asText());
        weatherData.put("cloudiness", node.get("clouds").get("all").asText());
        weatherData.put("visibility", node.get("visibility").asText());
        weatherData.put("cityName", node.get("name").asText());
        weatherData.put("country", node.get("sys").get("country").asText());

        return weatherData;
    }
}