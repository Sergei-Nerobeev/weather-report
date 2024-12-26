package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.model.UserRole;
import hu.nero.weather_report.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  protected static List<UserModel> users = new ArrayList<>();

  private final PasswordEncoder passwordEncoder;
  private final UsersRepository usersRepository;

  @PostConstruct
  public void postConstruct() {
    UserModel user = new UserModel();
    user.setRole(UserRole.ADMIN);
    user.setUsername("admin");
    user.setPassword(passwordEncoder.encode("admin"));
    users.add(user);
  }

  public UserModel register(String username, String password) {
    if (username == null || password == null) {
      throw new IllegalArgumentException("Username or password are empty!");
    } else {
      if(usersRepository.findFirstByUsername(username).isPresent()) {
        System.out.println("Duplicate username");
        return null;
      }
      UserModel userModel = new UserModel();
      userModel.setRole(UserRole.USER);
      userModel.setPassword(passwordEncoder.encode(password));
//      users.add(userModel);
      return usersRepository.save(userModel);
    }
  }

  public UserModel findByUsername(String username) {
    return users.stream()
                .filter(user -> user
                    .getUsername()
                    .equals(username))
                .findFirst()
                .orElse(null);

  }

  public UserModel findByUsernameInDataBase(String username) {
    return usersRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));

  }

  public UserModel authenticate(String username, String password) {
    return usersRepository.findByUsernameAndPassword(username, password).orElse(null);
  }
}
