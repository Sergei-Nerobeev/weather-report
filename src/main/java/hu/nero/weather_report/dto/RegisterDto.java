package hu.nero.weather_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

  @NonNull
  private String email;

  @NonNull
  private String password;

  private String confirmPassword;

  @NonNull
  private String role;
}
