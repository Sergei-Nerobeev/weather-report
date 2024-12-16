package hu.nero.weather_report.security;

import hu.nero.weather_report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserService userService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.cors(AbstractHttpConfigurer::disable);
    httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/info")
                    .authenticated()
                    .requestMatchers("/admin")
                    .hasRole("ADMIN")
                    .requestMatchers("/user")
                    .hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/", "/reports", "/home", "/create", "/report", "/auth", "/auth/login", "/auth/register")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/reports/create")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/create")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/reports/createReport")
                    .permitAll()
                    .anyRequest()
                    .authenticated())

                .formLogin(formLogin -> formLogin
                    .loginPage("/auth/login")
                    .failureUrl("/auth/login?error")
                    .loginProcessingUrl("/auth/process-login")
                )

                .logout(logout -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/"));

    return httpSecurity.build();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userService);
    return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // Заглушка
    return NoOpPasswordEncoder.getInstance();
  }

//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
