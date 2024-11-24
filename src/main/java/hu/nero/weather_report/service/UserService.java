package hu.nero.weather_report.service;

import hu.nero.weather_report.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static List<UserModel> users = new ArrayList<>();

    public void setUsers(UserModel user) {
        users.add(user);
    }

    public UserModel findByLogin(String login) {
        return users.stream().filter(user -> user.getEmail().equals(login))
                .findFirst().orElse(null);
    }
}
