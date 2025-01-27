package hu.nero.weather_report.controller.mvc;

import hu.nero.weather_report.model.ReportModel;
import hu.nero.weather_report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
      Model model,
      @AuthenticationPrincipal UserDetails userDetails) {

    String username = userDetails.getUsername();
    model.addAttribute(USER_NAME, username);

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
  @PreAuthorize("hasRole('ADMIN')")
  public String delete(@PathVariable Double lat) throws IllegalAccessException {
    reportService.delete(lat);
    return "redirect:/reports";
  }

}
