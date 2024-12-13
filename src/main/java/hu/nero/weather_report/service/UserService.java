package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.repository.RoleRepository;
import hu.nero.weather_report.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserModel registerUser(String username, String password) {

    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
      throw new IllegalArgumentException("User and password must not be empty");
    }
    else {
      if (userRepository.findByUsername(username).isPresent()) {
        System.out.println("Duplicate login");
        throw new IllegalArgumentException("User with this username already exists");
      }
      UserModel userModel = new UserModel();
      userModel.setUsername(username);
      userModel.setPassword(password);
      return userRepository.save(userModel);
    }
  }

  public UserModel authenticate(String username) {
    return userRepository.findByUsername(username).orElse(null); // changed it to html page or exception?
  }

  public Optional<UserModel> findByUserName(String username) {
    return userRepository.findByUsername(username);
  }

  /*
   * Преобразование userModel в Spring User
   *
   * */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel userModel = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(
        String.format("User '%s' not found", username)));

    return new User(
        userModel.getUsername(),
        userModel.getPassword(),
        userModel.getRoles().stream()
                 .map(roleModel -> new SimpleGrantedAuthority(roleModel.getName()))
                 .collect(Collectors.toList())
    );
  }

  public void createNewUser(UserModel userModel) {
    userModel.setRoles(List.of(roleRepository.findRoleByName("ROLE_USER").get()));
    userRepository.save(userModel);
  }
}
