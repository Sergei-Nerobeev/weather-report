package hu.nero.weather_report.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  @Autowired
  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.cors(AbstractHttpConfigurer::disable);
    httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/", "/reports", "/styles/*", "/home", "/create", "/report", "/login", "/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/create").permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/create").permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/createReport").permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/edit/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/editReport").permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/delete/*").permitAll()
                    .requestMatchers(HttpMethod.GET, "/weather").permitAll()
                    .requestMatchers(HttpMethod.POST, "/weather").permitAll()
                    .anyRequest().authenticated())

                .formLogin(formLogin -> formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/reports", true)
                    .permitAll())

                .logout(LogoutConfigurer::permitAll);

    return httpSecurity.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

}
