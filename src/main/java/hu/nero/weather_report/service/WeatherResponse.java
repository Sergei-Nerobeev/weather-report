package hu.nero.weather_report.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@Setter
public class WeatherResponse {
  @JsonProperty("coord")
  private Coord coord;

  @JsonProperty("weather")
  private List<Weather> weather;

  @JsonProperty("main")
  private Main main;

  @JsonProperty("base")
  private String base;

  @Getter
  @Setter
  public static class Coord {
    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;
  }

  @Getter
  @Setter
  public static class Weather {
    @JsonProperty("id")
    private int id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String icon;
  }

  @Getter
  @Setter
  public static class Main {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;
  }
}







