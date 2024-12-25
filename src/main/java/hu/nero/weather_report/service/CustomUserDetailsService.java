package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserModel byLogin = userService.findByUsername(username);
      UserModel byLogin = userService.findByUsernameInDataBase(username);
    if (username == null) {
      throw new UsernameNotFoundException("User == null");
    }
    return User.builder()
               .username(byLogin.getUsername())
               .password(byLogin.getPassword())
               .roles(byLogin.getRole().name())
               .build();
  }
}
