package p4_group_8_repo.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import p4_group_8_repo.controller.LoginController;
import p4_group_8_repo.model.user.UserRole;

import java.util.Objects;

/**
 * The {@code LoginView} provides a login screen for users to access the app.
 * It also serves as a simple example of how to implement {@code ManagedView}.
 * Note that this class extends {@code VBox} so that we can use JavaFX's built-in layout logic.
 */
public class LoginView extends VBox implements ManagedView {

  private final LoginController controller;
  protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
  private TextField txtUsername;
  private PasswordField txtPassword;

  public LoginView(LoginController controller) {
    this.controller = controller;
    this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
    buildView();
  }

  private void buildView() {
    Image logoImage = new Image(Objects.requireNonNull(getClass().getResource("/thisimage.png")).toExternalForm());
    ImageView logoImageView = new ImageView(logoImage);
    logoImageView.setSmooth(true);

    logoImageView.setFitWidth(350);
    logoImageView.setFitHeight(90);
    this.setSpacing(20);
    setStyle("-fx-background-color: #FDFBF9 ");

    Label loginLabel = new Label("Mentor App");
    Font fontLoginLabel = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40);
    loginLabel.setFont(fontLoginLabel);
    loginLabel.setTextFill(Color.rgb(16,38,59));
    Font fontText = Font.font("Arial",18);
    Label homePageLabel = new Label("Welcome to the Log in Page for the Mentor App!!!");
    Label username = new Label("Username: ");
    Label password = new Label("Password: ");
    homePageLabel.setFont(fontText);
    username.setFont(fontText);
    password.setFont(fontText);
    homePageLabel.setTextFill(Color.rgb(16,38,59));
    username.setTextFill(Color.rgb(16,38,59));
    password.setTextFill(Color.rgb(16,38,59));

    Label missingUser = new Label("");
    missingUser.setTextFill(Color.rgb(185, 28, 46));
    txtUsername = new TextField();
    txtUsername.setPromptText("Input Username");
    HBox usernameHBox = new HBox(10, username, txtUsername);
    usernameHBox.setAlignment(Pos.BASELINE_CENTER);
    txtPassword = new PasswordField();
    HBox passwordHBox = new HBox(10, password, txtPassword);
    passwordHBox.setAlignment(Pos.BASELINE_CENTER);
    txtPassword.setPromptText("Input password");
    Button btnLogin = new Button("Login");
    btnLogin.setFont(fontText);
    btnLogin.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");
    btnLogin.setOnAction(e -> {
      boolean success = controller.onLoginClick(txtUsername.getText(), txtPassword.getText());
      if (success) {
        var eh = onViewChange.get();
        txtUsername.setText("");
        txtPassword.setText("");
        missingUser.setText("");
        if (eh != null) {
          if(controller.getRole() == UserRole.MENTEE){
            eh.handle(new ViewChangeEvent(ViewManager.MENTEEP));
          }else if(controller.getRole() == UserRole.MENTOR){
            eh.handle(new ViewChangeEvent(ViewManager.MENTORP));
          }else{
            eh.handle(new ViewChangeEvent(ViewManager.ADMINP));
          }
        }
      }else{
        missingUser.setText("This user does not exist. Input valid username and password!!!");
      }
    });
    setPrefWidth(600);
    setPrefHeight(500);

    setMinWidth(600);
    setMinHeight(500);
    setMaxWidth(600);
    setMaxHeight(500);

    getChildren().addAll(logoImageView, loginLabel, homePageLabel, usernameHBox, passwordHBox, missingUser, btnLogin);
    setPadding(new Insets(50,30,20,30));
    setAlignment(Pos.BASELINE_CENTER);
  }

  @Override
  public EventHandler<? super ViewChangeEvent> getOnViewChange() {
    return onViewChange.get();
  }

  @Override
  public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
    onViewChange.set(eventHandler);
  }
}