package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

  private static final String USER_NAME = "username";
  private final ReportService reportService;

  @GetMapping
  public String getReportPage(
      Model model,
      @AuthenticationPrincipal UserDetails userDetails) {

    String username = userDetails.getUsername();
    model.addAttribute(USER_NAME, username);
    return "report_page";
  }
}
