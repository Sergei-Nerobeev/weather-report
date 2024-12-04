package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserModel registerUser(String login, String password) {

    if (login == null && password == null) {
      return null; // changed it to html page
    }
    else {
      UserModel userModel = new UserModel();
      userModel.setLogin(login);
      userModel.setPassword(password);
      return userRepository.save(userModel);
    }
  }
  public UserModel authenticate(String login, String password) {
    return userRepository.findByLoginAndPassword(login, password).orElse(null); // changed it to html page
  }
}
