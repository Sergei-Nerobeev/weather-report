package hu.nero.weather_report.repository;

import hu.nero.weather_report.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
  public UserModel findByEmail(String email);
}
