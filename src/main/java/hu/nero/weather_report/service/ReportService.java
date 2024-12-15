package hu.nero.weather_report.service;

import hu.nero.weather_report.model.ReportModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

  private static final List<ReportModel> reports;

  static {

    reports = new ArrayList<>();
    reports.add(new ReportModel(22.2, 22.2, "Key"));
    reports.add(new ReportModel(32.2, 22.2, "Key"));
    reports.add(new ReportModel(42.2, 22.2, "Key"));
  }

  public List<ReportModel> getAllReportsByUsername(String username) {
    if (username != null) {
      return reports;
    }
    return reports.stream().filter(lat ->lat.getLat() > 30.2).toList();
  }
  public void save(ReportModel report){
    reports.add(report);
  }

}
