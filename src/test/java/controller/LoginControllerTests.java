package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p4_group_8_repo.controller.LoginController;
import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.Mentee;
import p4_group_8_repo.model.user.User;

import java.util.Optional;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTests {

  private Repository<User, String> model;
  private LoginController controller;

  @BeforeEach
  public void setUp() {
    model = mock();
    controller = new LoginController(model);
  }

  @Test
  public void testLoginClickMissingUser() {
    when(model.selectById("user")).thenReturn(Optional.empty());//says when searching for someone with id "user" then, return nothing(basically, that the user does not exist)
    boolean loginResult = controller.onLoginClick("user", "password");//thus passes in arguments(username and password of the user)
    // into the controller.onLoginClick() funcn. and acts like it is the user that put this in the textfield
    assertFalse(loginResult); // the loginResult holds false because there is no such user.
  }

  @Test
  public void testLoginClickBadPassword() {
    when(model.selectById("user")).thenReturn(Optional.of(new Mentee("user", "password")));
    boolean loginResult = controller.onLoginClick("user", "logmein");
    assertFalse(loginResult);
  }

  @Test
  public void testLoginClickBadUsername() {
    when(model.selectById("user")).thenReturn(Optional.of(new Mentee("user", "password")));
    boolean loginResult = controller.onLoginClick("sheilah", "password");
    assertFalse(loginResult);
  }

  @Test
  public void testLoginClickGood() {
    when(model.selectById("user")).thenReturn(Optional.of(new Mentee("user", "password")));
    boolean loginResult = controller.onLoginClick("user", "password");
    assertTrue(loginResult);
  }


}
