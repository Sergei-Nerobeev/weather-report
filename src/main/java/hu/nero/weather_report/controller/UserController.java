package hu.nero.weather_report.controller;

import hu.nero.weather_report.dto.RegisterDto;
import hu.nero.weather_report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Autowired

  @GetMapping("/")
  public String homePage() {
    return "index";
  }

  @GetMapping("/register")
  public String register(Model model) {
    RegisterDto registerDto = new RegisterDto();
    model.addAttribute(registerDto);
    return "register";
  }

}
