package hu.nero.weather_report.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "weather.api")
@Getter
@Configuration
public class OpenWeatherProperties {
  private String url;
  private String key;

}
