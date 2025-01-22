package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.model.UserRole;
import hu.nero.weather_report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
  private final CustomUserDetailsService customUserDetailsService;

  @Autowired
  public UserService(UserRepository userRepository, CustomUserDetailsService customUserDetailsService) {
    this.userRepository = userRepository;
    this.customUserDetailsService = customUserDetailsService;
  }

  public UserModel register(UserModel userModel) {
    boolean result = isUserInDataBase(userModel);
    if (!result) {
      userModel.setRole(UserRole.USER);
      userModel.setUsername(userModel.getUsername());
      userModel.setPassword(encoder.encode(userModel.getPassword()));
      return userRepository.save(userModel);
    }
    throw new UsernameNotFoundException("User not found");
  }

//  public UserModel register(UserModel userModel) {
//    var result = customUserDetailsService.loadUserByUsername(userModel.getUsername());
//    if (!result.getUsername().equals(userModel.getUsername())) {
//      userModel.setRole(UserRole.USER);
//      userModel.setUsername(userModel.getUsername());
//      userModel.setPassword(encoder.encode(userModel.getPassword()));
//      return userRepository.save(userModel);
//    }
//    throw new UsernameNotFoundException("User not found");
//  }

  public boolean isUserInDataBase(UserModel userModel) {
    UserModel userFromDb = userRepository.findByUsername(userModel.getUsername());
      return userFromDb != null && userFromDb.getUsername().equals(userModel.getUsername());
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
