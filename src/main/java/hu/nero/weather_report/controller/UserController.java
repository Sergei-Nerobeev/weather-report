package hu.nero.weather_report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @GetMapping("/home")
  public String homePage() {
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

}
