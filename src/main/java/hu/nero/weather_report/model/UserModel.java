package hu.nero.weather_report.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
  @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "users_seq", allocationSize = 1)
  Integer id;

  @Column
  String login;

  @Column
  String password;



}
