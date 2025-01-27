package hu.nero.weather_report.repository;

import hu.nero.weather_report.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository <LocationModel, Integer> {

  Optional<LocationModel> findLocationModelById(Integer id);
}
