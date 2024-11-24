package hu.nero.weather_report.rest;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/")
    public String mainPage()  {
        return "main_page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserModel user) {
        userService.setUsers(user);
        return "redirect:/login?success";
    }

}
