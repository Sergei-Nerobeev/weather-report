package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.exception.PlaceNotFoundException;
import hu.nero.weather_report.model.WeatherResponse;
import hu.nero.weather_report.service.WeatherJsonHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")
public class WeatherController {

  private final Logger logger = LoggerFactory.getLogger(WeatherController.class);
  private final WeatherJsonHttpClient weatherJsonHttpClient;

  @Autowired
  public WeatherController(WeatherJsonHttpClient weatherJsonHttpClient) {
    this.weatherJsonHttpClient = weatherJsonHttpClient;
  }

  @PostMapping
  public String getWeatherReport(@RequestParam("location") String location, Model model) {
    WeatherResponse weatherResponse = weatherJsonHttpClient.getWeatherData(location);
    if (weatherResponse.getWeather() == null || location == null) {
      logger.error("Place not found: {}", location);
      throw new PlaceNotFoundException(StringTemplate.STR."Place not found: \{location}");
    }
    model.addAttribute("location", location);
    model.addAttribute("weatherResponse", weatherResponse);
    logger.info("Weather data added to model: {}", StringTemplate.STR."\{location} \{weatherResponse}");

    return "weather_report";
  }

}
