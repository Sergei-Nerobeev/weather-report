package hu.nero.weather_report.rest;

import hu.nero.weather_report.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/users")
public class UserRestControllerV1 {

    List<User> users = Stream.of(
            new User(1, "john@gmail.com"),
            new User(2, "ann@mail.ru"),
            new User(3, "bill@yandex.ru")
    ).collect(Collectors.toList());

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return users.stream().filter(user -> user.getId()
                .equals(id)).findFirst().orElse(null);
    }

}
