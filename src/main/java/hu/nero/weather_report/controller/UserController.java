package hu.nero.weather_report.controller;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.service.UserService;
//import hu.nero.weather_report.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {

  private final UserService userService;
//  UserDetailsServiceImpl userDetailsService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("registerRequest", new UserModel());
    return "register_page";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute UserModel userModel) {
    System.out.println("register request: " + userModel);
    UserModel registeredUser = userService.registerUser(userModel.getUsername(),userModel.getPassword());
    return registeredUser == null ? "error_page" : "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("loginRequest", new UserModel());
    return "login_page";
  }
//
//  @PostMapping("/login")
//  public String login(@ModelAttribute UserModel userModel, Model model) {
//    System.out.println("register request: " + userModel);
//    UserModel authenticated = userService.authenticate(userModel.getLogin());
//    if (authenticated != null) {
//      model.addAttribute("userLogin", authenticated.getLogin());
//      return "personal_page";
//    }
//    else {
//      return "error_page";
//    }
//  }

  @PostMapping("/login")
  public String login(@ModelAttribute UserModel userModel, Model model) {
    System.out.println("register request: " + userModel);
    UserModel authenticated = userService.authenticate(userModel.getUsername());
    if (authenticated != null) {
      model.addAttribute("username", authenticated.getUsername());
      return "personal_page";
    }
    else {
      return "error_page";
    }
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return "redirect:/login";
  }

}