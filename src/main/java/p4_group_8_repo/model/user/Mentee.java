package p4_group_8_repo.model.user;

import java.time.LocalDateTime;

public class Mentee implements User, HasCv, HasAvailability {

  private UserRole role = UserRole.MENTEE;
  private String username, password, cvText;
  private LocalDateTime startAvailability, endAvailability;

  public Mentee(String username, String password) {
    this(username, password, "", null, null);
  }

  public Mentee(String username, String password, String cvText, LocalDateTime startAvailability, LocalDateTime endAvailability) {
    this.username = username;
    this.password = password;
    this.cvText = cvText;
    this.startAvailability = startAvailability;
  }

  @Override
  public String getCvText() {
    return cvText;
  }

  @Override
  public void setCvText(String text) {
    cvText = text;
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
  public UserRole getRole() {
    return role;
  }

  @Override
  public void setRole(UserRole role) {
    this.role = role;
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
  public boolean equals(Object obj) {
    if (obj instanceof Mentee m) {
      return username.equals(m.username);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }
}

//Note: Can add start availability function and end availability function from Mentor view into Mentee view
// so that in the admin page, when a mentee is picked. Only mentors that have same availability date as the selected mentee show up
