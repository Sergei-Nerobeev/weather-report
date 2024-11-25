package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserModel userModel = userRepository.findByEmail(email);

      if (userModel != null) {
        return User.withUsername(userModel.getEmail())
          .password(userModel.getPassword())
          .roles(userModel.getRole())
          .build();
      }
    return null;
  }

  }

