package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.client.JsonHttpClient;
import hu.nero.weather_report.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {

  private final WeatherResponse weatherResponse;

  @Autowired
  public WeatherController(WeatherResponse weatherResponse) {
    this.weatherResponse = weatherResponse;
  }

  @GetMapping
  public String getWeather(Model model) {
    try {
      JsonHttpClient jsonHttpClient = new JsonHttpClient(weatherResponse);
      WeatherResponse weatherResponses = jsonHttpClient.getWeatherData();

      model.addAttribute("weatherResponse", weatherResponses.toString());
      System.out.println(weatherResponses.toString());

    }
    catch (Exception exception) {
      exception.printStackTrace();
      model.addAttribute("error", "Unable to fetch weather data");
    }
    return "weather_report";
  }


}
