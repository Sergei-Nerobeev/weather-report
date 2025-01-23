package hu.nero.weather_report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

//  @JsonProperty("coord")
//  private Coord coord;

  @JsonProperty("weather")
  private List<Weather> weather;

  @JsonProperty("main")
  private Main main;

//  @JsonProperty("base")
//  private String base;

//  @Getter
//  @Setter
//  @ToString
//  public static class Coord {
//    @JsonProperty("lon")
//    private double lon;
//
//    @JsonProperty("lat")
//    private double lat;
//  }

  @Getter
  @Setter
  @ToString
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
 @ToString
  public static class Main {
    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("feels_like")
    public Double feelsLike;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    @JsonProperty("sea_level")
    private int seaLevel;

    @JsonProperty("grnd_level")
    private int grndLevel;
  }
}











