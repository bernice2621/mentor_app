package p4_group_8_repo.model.user;

import p4_group_8_repo.model.HasId;

import java.time.LocalDateTime;

/**
 * Base interface for any user of the system. Uses the username as id.
 */
public interface User extends HasId<String> {

  default String getId() {
    return getUsername();
  }

  default void setId(String id) {
    setUsername(id);
  }

  UserRole getRole();

  void setRole(UserRole role);

  String getUsername();

  void setUsername(String username);

  String getPassword();

  void setPassword(String password);

  boolean isAvailable();

  LocalDateTime getStartAvailability();

  void setStartAvailability(LocalDateTime date);

  LocalDateTime getEndAvailability();

  void setEndAvailability(LocalDateTime date);

}
