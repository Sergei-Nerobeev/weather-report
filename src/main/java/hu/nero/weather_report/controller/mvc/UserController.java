package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String getLoginPage() {
    return "login_page";
  }

  @GetMapping("/register")
  public String getRegistrationPage(Model model) {
    model.addAttribute("username", new UserModel());
    return "register_page";
  }

  @PostMapping("/register")
  public String registerUser(UserModel userModel, Model model) {
    if (!userService.findUserInDataBase(userModel)) {
      userService.register(userModel);
      return "redirect:/login?success";
    }
    else {
      return "error_page";
    }
  }


}
