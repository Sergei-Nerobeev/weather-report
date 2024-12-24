//package hu.nero.weather_report.config;
//
//import hu.nero.weather_report.model.UserModel;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//
//public record UserDetailsImpl(UserModel userModel) implements UserDetails {
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return Collections.emptyList();
//  }
//
//  @Override
//  public String getPassword() {
//    return this.userModel.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return this.userModel.getUsername();
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
//
//}
