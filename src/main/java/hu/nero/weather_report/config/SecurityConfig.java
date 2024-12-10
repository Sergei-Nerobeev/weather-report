package hu.nero.weather_report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/").permitAll().anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/login").permitAll())
        .logout(LogoutConfigurer::permitAll);
//            .requestMatchers("/logout").authenticated().anyRequest());
//            .anyRequest()
//            .authenticated())



//        .formLogin(form -> form
//            .loginPage("/login")
//            .defaultSuccessUrl("/home", true)
//            .permitAll()
//        )
//        .logout(LogoutConfigurer::permitAll);

    return http.build();
  }
}
