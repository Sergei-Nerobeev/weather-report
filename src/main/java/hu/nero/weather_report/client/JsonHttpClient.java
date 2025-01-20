package hu.nero.weather_report.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nero.weather_report.response.WeatherResponse;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
public class JsonHttpClient {

  private final String CURRENCY_URL =
      "https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=103f46d7699fbe29a360080cef60332a&units=metric";
  private final String URL = "https://api.openweathermap.org/data/2.5/weather";
  private final String PLACE = "Budapest";
  private final String API_KEY = "103f46d7699fbe29a360080cef60332a";
  private final String METRIC = "metric";
  private final String FULL_URL = StringTemplate.STR."\{URL}?q=\{PLACE}&appid=\{API_KEY}&units=\{METRIC}";
  private WeatherResponse weatherResponse;

  public WeatherResponse getWeatherData() {
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
                                     .uri(URI.create(FULL_URL))
                                     .GET()
                                     .build();
    HttpResponse<String> response = null;
    try {
      response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      ObjectMapper objectMapper = new ObjectMapper();
      weatherResponse = objectMapper.readValue(response.body(), WeatherResponse.class);
      return weatherResponse;

    }
    catch (IOException | InterruptedException exception) {
      throw new RuntimeException(exception);
    }
  }
}



