package p4_group_8_repo.model.user;

import java.time.LocalDateTime;

public class Administrator implements User {

  private UserRole role = UserRole.ADMIN;
  private String username, password;

  public Administrator(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public UserRole getRole() {
    return role;
  }

  @Override
  public void setRole(UserRole role) {
    this.role = role;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean isAvailable() {
    return false;
  }

  @Override
  public LocalDateTime getStartAvailability() {
    return null;
  }

  @Override
  public void setStartAvailability(LocalDateTime date) {}

  @Override
  public LocalDateTime getEndAvailability() {
    return null;
  }

  @Override
  public void setEndAvailability(LocalDateTime date) {

  }
}
