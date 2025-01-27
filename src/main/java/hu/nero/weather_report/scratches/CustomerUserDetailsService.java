//package hu.nero.weather_report.config;
//
//import hu.nero.weather_report.model.UserModel;
//import hu.nero.weather_report.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomerUserDetailsService implements UserDetailsService {
//
//  private final UserRepository userRepository;
//
//  @Autowired
//  public CustomerUserDetailsService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<UserModel> userModel = userRepository.findFirstByLogin(username);
//    if (userModel.isEmpty()) {
//      throw new UsernameNotFoundException("User not found");
//    }
//    return new UserDetailsImpl(userModel.get());
//  }
//}

