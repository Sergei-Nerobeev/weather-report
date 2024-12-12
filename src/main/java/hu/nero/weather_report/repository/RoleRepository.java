package hu.nero.weather_report.repository;

import hu.nero.weather_report.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

  Optional<RoleModel> findRoleByName(String name);
}
