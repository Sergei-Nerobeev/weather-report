package hu.nero.weather_report.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonHttpClient {

  private final String CURRENCY_URL = "https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=103f46d7699fbe29a360080cef60332a&units=metric";
  private final String URL = "https://api.openweathermap.org/data/2.5/weather";
  private final String PLACE = "Budapest";
  private final String API_KEY = "103f46d7699fbe29a360080cef60332a";
  private final String METRIC = "metric";

  public String getCurrencyUrl() { // дописать сюда headers
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(CURRENCY_URL))
        .GET().build();
    HttpResponse<String> response = null;
    try{
      response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException exception) {
      throw new RuntimeException(exception);
    }
    return response.body();
  }
}
