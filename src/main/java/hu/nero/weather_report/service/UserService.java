package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.UserRepository;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@ToString
public class UserService {

  private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  public UserModel registerUser(String login, String password) {

    if (login == null && password == null) {
      return null; // changed it to html page?
    }
    else {
      if(userRepository.findFirstByLogin(login).isPresent()) {
        System.out.println("Duplicate login");
        return null; // return?
      }
      UserModel userModel = new UserModel();
      userModel.setLogin(login);
      userModel.setPassword(password);
      return userRepository.save(userModel);
    }
  }
  public UserModel authenticate(String login, String password) {
    return userRepository.findByLoginAndPassword(login, password).orElse(null); // changed it to html page?
  }
}
