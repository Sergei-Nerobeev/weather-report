package hu.nero.weather_report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nero.weather_report.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherJsonHttpClient {

  @Value("${weather.api.url}")
  private String url;

  @Value("${weather.api.key}")
  private String apiKey;

//  @Value("${weather.api.place}")
//  private String place;

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  @Autowired
  public WeatherJsonHttpClient(HttpClient httpClient, ObjectMapper objectMapper) {
    this.httpClient = httpClient;
    this.objectMapper = objectMapper;
  }

  public WeatherResponse getWeatherData(String place) {

    HttpRequest request = HttpRequest.newBuilder()
                                     .uri(URI.create(StringTemplate.STR."\{url}?q=\{place}&appid=\{apiKey}&units=metric"))
                                     .GET()
                                     .build();
    HttpResponse<String> response = null;
    try {
      response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return objectMapper.readValue(response.body(), WeatherResponse.class);
    }
    catch (IOException | InterruptedException exception) {
      throw new RuntimeException("Failed to fetch weather data", exception);
    }
  }
}



