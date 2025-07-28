package p4_group_8_repo.controller;

import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.*;

import java.util.Optional;

public class LoginController {

  private final Repository<User, String> repo;
  public UserRole role;

  public LoginController(Repository<User, String> model) {
    this.repo = model;
  }

  public boolean onLoginClick(String username, String password) {
    Optional<User> user = repo.selectById(username);
    if (user.isEmpty()) {
      return false;
    }
    User u = user.get();
    if(u instanceof Administrator adm){
      role = adm.getRole();
    }else if(u instanceof Mentor mentor){
      role = mentor.getRole();
    }else if(u instanceof Mentee mentee){
      role = mentee.getRole();
    }
    return u.getUsername().equals(username) && u.getPassword().equals(password);
  }

  public UserRole getRole() {
    return role;
  }

}
