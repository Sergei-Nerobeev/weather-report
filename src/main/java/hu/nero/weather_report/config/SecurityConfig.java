//package hu.nero.weather_report.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//public class SecurityConfig {
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(User.withUsername("user")
//                           .password(passwordEncoder().encode("password"))
//                           .roles("USER")
//                           .build());
//    manager.createUser(User.withUsername("admin")
//                           .password(passwordEncoder().encode("1234"))
//                           .roles("ADMIN")
//                           .build());
//    return manager;
//  }
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .authorizeHttpRequests(authorize -> authorize
//            .requestMatchers("/login", "/public/**").permitAll()
//            .requestMatchers("/admin/**").hasRole("ADMIN")
//            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//            .anyRequest().authenticated()
//        )
//        .formLogin(form -> form
//            .loginPage("/login")
//            .defaultSuccessUrl("/home", true)
//            .permitAll()
//        )
//        .logout(LogoutConfigurer::permitAll);
//
//    return http.build();
//  }
//
//}
