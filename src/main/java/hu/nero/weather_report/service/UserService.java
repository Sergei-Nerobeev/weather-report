package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.model.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  protected static List<UserModel> users = new ArrayList<>();

  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void postConstruct() {
    UserModel user = new UserModel();
    user.setRole(UserRole.ADMIN);
    user.setUsername("admin");
    user.setPassword(passwordEncoder.encode("admin"));
    users.add(user);
  }

  public void register(UserModel user) {
    if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
      throw new IllegalArgumentException("WARN");
    }
    user.setRole(UserRole.USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    users.add(user);
  }

  public UserModel findByUsername(String username) {
    return users.stream().filter(user -> user.getUsername()
                                             .equals(username))
                .findFirst()
                .orElse(null);
  }
}
