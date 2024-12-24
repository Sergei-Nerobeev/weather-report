package hu.nero.weather_report.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportModel {

  private Double lat; // Широта

  private Double lon; // Долгота

  private String appId; // Ключ

}
