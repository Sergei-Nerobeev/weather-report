package hu.nero.weather_report.validator;

import hu.nero.weather_report.model.UserModel;
import hu.nero.weather_report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


  private final UserService userService;

  @Autowired
  public UserValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return UserModel.class.equals(clazz);
  }

  @Override
  public void validate(Object obj, Errors errors) {
    UserModel userModelValidate = (UserModel) obj;
    UserDetails userFromDB = userService.loadUserByUsername(userModelValidate.getUsername());
    if(userFromDB.isEnabled()) {
      errors.rejectValue("username", "400","Username already exists");
    }
  }
}
