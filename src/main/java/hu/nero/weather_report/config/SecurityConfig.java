//package hu.nero.weather_report.config;
//
//import hu.nero.weather_report.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  private final UserDetailsServiceImpl userDetailsService;
//
//  @Autowired
//  public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//    this.userDetailsService = userDetailsService;
//  }
//
//  @Bean
//  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//    http
//        // authorization and security config
//        .authorizeHttpRequests(authorize -> authorize
//        .requestMatchers("/","/login","/register","/error_page").permitAll()
//        .anyRequest().authenticated())
//        .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/process-login")
//        .defaultSuccessUrl("/personal_page",true)
//        .failureForwardUrl("/login?error"));
//    return http.build();
//  }

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**");
//  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder(){
//    return new BCryptPasswordEncoder();
//  }
//}
