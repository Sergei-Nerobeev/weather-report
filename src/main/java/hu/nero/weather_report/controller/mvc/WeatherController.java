package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.client.JsonHttpClient;
import hu.nero.weather_report.response.WeatherResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {

  @GetMapping
  public String getWeather(Model model) {
    try {
      JsonHttpClient jsonHttpClient = new JsonHttpClient();
      WeatherResponse weatherResponses = jsonHttpClient.getWeatherData();

      model.addAttribute("weatherResponse", weatherResponses);
      System.out.println(weatherResponses);

    }
    catch (Exception exception) {
      exception.printStackTrace();
      model.addAttribute("error", "Unable to fetch weather data");
    }
    return "weather_report";
  }


}
