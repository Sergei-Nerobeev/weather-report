package hu.nero.weather_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

  private String email;

  private String password;

  private String confirmPassword;

  private String role;
}
