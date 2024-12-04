package hu.nero.weather_report.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Controller
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  String login;
  String password;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ?
                               proxy.getHibernateLazyInitializer().getPersistentClass() :
                               o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ?
                                  proxy.getHibernateLazyInitializer().getPersistentClass() :
                                  this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    UserModel that = (UserModel) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy proxy ?
           proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() :
           getClass().hashCode();
  }

}
