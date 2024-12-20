package hu.nero.weather_report.config;

import hu.nero.weather_report.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.cors(AbstractHttpConfigurer::disable);
    httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/admin")
                    .hasRole("ADMIN")
                    .requestMatchers("/user")
                    .hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/", "/reports", "/styles/*", "/home", "/create", "/report", "/login", "/register")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/create")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/create")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/createReport")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/edit/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/editReport")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/delete/*")
                    .permitAll()
                    .anyRequest()
                    .authenticated())

                .formLogin(formLogin -> formLogin
                    .loginPage("/login").permitAll())


                .logout(LogoutConfigurer::permitAll);

    return httpSecurity.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    return authenticationManagerBuilder.build();
  }

}
