package hu.nero.weather_report.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "locations")

public class LocationModel {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Column(name = "latitude", nullable = false, precision = 10, scale = 6)
  private BigDecimal latitude;

  @Column(name = "longitude", nullable = false, precision = 10, scale = 6)
  private BigDecimal longitude;

}
