package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.model.UserRole;
import hu.nero.weather_report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public UserModel register(UserModel userModel) {
    userModel.setRole(UserRole.USER);
    userModel.setUsername(userModel.getUsername());
    userModel.setPassword(encoder.encode(userModel.getPassword()));
    return userRepository.save(userModel);
  }

  public boolean findUserInDataBase(UserModel userModel) {
    UserModel userFromDb = userRepository.findByUsername(userModel.getUsername());
    return userFromDb == null || userModel.getUsername().equals(userFromDb.getUsername());
  }

  //  protected static List<UserModel> users = new ArrayList<>();

//  private final PasswordEncoder passwordEncoder;
//
//  @PostConstruct
//  public void postConstruct() {
//    UserModel user = new UserModel();
//    user.setRole(UserRole.ADMIN);
//    user.setUsername("admin");
//    user.setPassword(passwordEncoder.encode("admin"));
//    users.add(user);
//  }

}
