package p4_group_8_repo.model.user;

import java.time.LocalDateTime;

public class Mentor implements User, HasAvailability {

  private UserRole role = UserRole.MENTOR;
  private String username, password;
  private LocalDateTime startAvailability, endAvailability;

  public Mentor(String username, String password) {
    this(username, password, null, null);
  }

  public Mentor(String username, String password, LocalDateTime startAvailability,LocalDateTime endAvailability) {
    this.username = username;
    this.password = password;
    this.startAvailability = startAvailability;
    this.endAvailability = endAvailability;
  }

  public Mentor(String username, LocalDateTime startAvailability, LocalDateTime endAvailability) {
    this.username = username;
    this.startAvailability = startAvailability;
    this.endAvailability = endAvailability;
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
  public LocalDateTime getStartAvailability() {
    return startAvailability;
  }

  @Override
  public void setStartAvailability(LocalDateTime date) {
    this.startAvailability = date;
  }

  @Override
  public LocalDateTime getEndAvailability() {
    return endAvailability;
  }

  @Override
  public void setEndAvailability(LocalDateTime date) {
    this.endAvailability = date;
  }

  @Override
  public boolean isAvailable( ){
    if(startAvailability.isAfter(LocalDateTime.of(2024, 1,1,0,0))){
      if(endAvailability.isAfter(startAvailability) && endAvailability.isAfter(LocalDateTime.of(2024, 1,1,0,0))){
        return true;
      }
    }
    return false;
  }

  public String toString() {
    return String.format("%s, %s, %s", username, startAvailability, endAvailability);
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
  public boolean equals(Object obj) {
    if (obj instanceof Mentor m) {
      return username.equals(m.username);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }
}
