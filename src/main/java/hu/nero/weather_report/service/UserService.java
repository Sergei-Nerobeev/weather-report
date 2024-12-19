package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  public static List<UserModel> users = new ArrayList<>();

  private final PasswordEncoder passwordEncoder;

  public void register(UserModel user) {
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
