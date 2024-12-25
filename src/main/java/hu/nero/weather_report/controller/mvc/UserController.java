package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public String getLoginPage() {
    return "login_page";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute UserModel userModel) {
    UserModel authenticated = userService.authenticate(userModel.getUsername(), userModel.getPassword());
    if (authenticated != null) {
      return "report_page";
    }
    else {
      return "error_page";
    }
  }

  @GetMapping("/register")
  public String getRegistrationPage(Model model) {
    model.addAttribute("username", new UserModel());
    return "register_page";
  }

  @PostMapping("/register")
  public String registerUser(@ModelAttribute UserModel user, Model model) {
    model.addAttribute("successMessage", "User successfully registered");
    userService.register(user);
    return "redirect:/login?success";
  }

}
