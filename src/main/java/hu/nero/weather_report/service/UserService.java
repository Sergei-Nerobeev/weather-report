package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserModel registerUser(String username, String password) {

    if (username == null && password == null) {
      return null; // changed it to html page?
    }
    else {
      if (userRepository.findFirstByusername(username).isPresent()) {
        System.out.println("Duplicate login");
        return null; // return?
      }
      UserModel userModel = new UserModel();
      userModel.setUsername(username);
      userModel.setPassword(password);
      return userRepository.save(userModel);
    }
  }

  public UserModel authenticate(String username) {
    return userRepository.findFirstByusername(username).orElse(null); // changed it to html page?
  }
}
