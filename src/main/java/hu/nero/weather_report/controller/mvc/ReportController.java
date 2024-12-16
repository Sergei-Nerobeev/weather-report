package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.model.ReportModel;
import hu.nero.weather_report.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

  private static final String USER_NAME = "username";
  private final ReportService reportService;

  @GetMapping
  public String getReportPage(
      @RequestParam(required = false, name = "username") String username,
      @RequestParam(required = false) String password,
      Model model,
      HttpServletRequest request) {

    HttpSession session = request.getSession();
    if (username != null && !username.isEmpty()) {
      session.setAttribute(USER_NAME, username);
    }
    String userName = (String) session.getAttribute(USER_NAME);
    model.addAttribute(USER_NAME, userName);

    List<ReportModel> reports = reportService.getAllReportsByUsername(username);
    model.addAttribute("userReports", reports);
    return "report_page";

  }

  @GetMapping("/create")
  public String getCreateReportPage(Model model) {
    model.addAttribute("newReport", new ReportModel());
    return "create_report_page";
  }

  @PostMapping("/createReport")
  public String createReport(@ModelAttribute ReportModel report) {
    reportService.save(report);
    return "redirect:/reports";
  }

  @GetMapping("/edit/{lat}")
  public String getEditBookPage(Model model, @PathVariable Double lat) throws IllegalAccessException {
    ReportModel byLatitude = reportService.findByLatitudeAndDelete(lat);
    model.addAttribute("reportToEdit", byLatitude);
    return "edit_report_page";
  }

  @PostMapping("/editReport")
  public String editBook(@ModelAttribute ReportModel report) {
    reportService.edit(report);
    return "redirect:/reports";
  }

  @GetMapping("/delete/{lat}")
  public String delete(@PathVariable Double lat) throws IllegalAccessException {
    reportService.delete(lat);
    return "redirect:/reports";
  }

}
