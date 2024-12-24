package hu.nero.weather_report.repository;

import hu.nero.weather_report.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

  Optional<UserModel> findByUsername(String username);
}
