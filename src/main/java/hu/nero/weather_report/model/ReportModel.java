package hu.nero.weather_report.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {

  private Double lat; // Широта

  private Double lon; // Долгота

  private String appId; // Ключ

}
