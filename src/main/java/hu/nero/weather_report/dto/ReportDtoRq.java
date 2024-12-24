package hu.nero.weather_report.dto;

import jakarta.validation.constraints.NotNull;

public class ReportDtoRq {

  @NotNull
  private Double lat; // Широта

  @NotNull
  private Double lon; // Долгота

  @NotNull
  private String appId; // Ключ
}
