package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.client.JsonHttpClient;
import hu.nero.weather_report.service.WeatherResponse;
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
      WeatherResponse weatherView = (WeatherResponse) weatherResponse.getWeather();
      JsonHttpClient jsonHttpClient = new JsonHttpClient();
      var json = jsonHttpClient.getCurrencyUrl();

      model.addAttribute("weather", weatherView);
      System.out.println(json);

    }
    catch (Exception exception) {
      exception.printStackTrace();
      model.addAttribute("error", "Unable to fetch weather data");
    }
    return "weather_report";
  }


}
