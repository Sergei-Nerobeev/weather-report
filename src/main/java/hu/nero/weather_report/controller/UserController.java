package hu.nero.weather_report.controller;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.UserRepository;
import hu.nero.weather_report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;

@Controller
public class UserController {

  private final UserService userService;

  private final DataSource dataSource;

  private final UserRepository userRepository;

  @Autowired
  public UserController(UserService userService,
                        @Qualifier("dataSource") DataSource dataSource,
                        UserRepository userRepository) {
    this.userService = userService;
    this.dataSource = dataSource;
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String getHomePage() {
    return "index";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("registerRequest", new UserModel());
    return "register_page";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("loginRequest", new UserModel());
    return "login_page";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute UserModel userModel) {
    System.out.println("register request: " + userModel);
    UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword());
    return registeredUser == null ? "error_page" : "redirect:/login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute UserModel userModel, Model model) {
    System.out.println("register request: " + userModel);
    UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
    if (authenticated != null) {
      model.addAttribute("userLogin", authenticated.getLogin());
      return "personal_page";
    }
    else {
      return "error_page";
    }
  }

}