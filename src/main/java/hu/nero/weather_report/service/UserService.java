//package hu.nero.weather_report.service;
//
//import hu.nero.weather_report.model.UserModel;
//import hu.nero.weather_report.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//  private final UserRepository userRepository;
//
//  public UserService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }
//
//  public UserModel registerUser(String username, String password) {
//
//    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
//      throw new IllegalArgumentException("User and password must not be empty");
//    }
//    else {
//      if (userRepository.findFirstByusername(username).isPresent()) {
//        System.out.println("Duplicate login");
//        throw new IllegalArgumentException("User with this username already exists");
//      }
//      UserModel userModel = new UserModel();
//      userModel.setUsername(username);
//      userModel.setPassword(password);
//      return userRepository.save(userModel);
//    }
//  }
//
//  public UserModel authenticate(String username) {
//    return userRepository.findFirstByusername(username).orElse(null); // changed it to html page or exception?
//  }
//}
