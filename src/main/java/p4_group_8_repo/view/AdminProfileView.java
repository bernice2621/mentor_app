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
import p4_group_8_repo.controller.AdminController;

import java.util.List;
import java.util.Objects;

public class AdminProfileView extends VBox implements ManagedView{
    private final AdminController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private TextField txtCurrentUsername, txtNewPassword;
    private PasswordField txtCurrentPassword, txtConfirmPassword;
    private Font fontText = Font.font("Arial", FontWeight.BOLD,18);
    private Color nottinghamBlue = Color.rgb(16,38,59);
    private Color nottinghamRed = Color.rgb(185, 28, 46);
    private Font fontButton = Font.font("Arial",  FontWeight.BOLD,14);

    public AdminProfileView(AdminController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {
        this.setSpacing(20);

        Label changeSuccessful = new Label("");
        changeSuccessful.setTextFill(nottinghamBlue);
        changeSuccessful.setFont(fontButton);
        Label warning = new Label("");
        warning.setTextFill(nottinghamRed);
        warning.setFont(fontButton);
        Label currentUsername = new Label("Current Username");
        Label currentPassword = new Label("Current Password");
        Label newPassword = new Label("New Password");
        Label confirmPassword = new Label("Confirm New Password");
        List<Label> labels = List.of(currentUsername, currentPassword, newPassword, confirmPassword);

        for (Label label : labels) {
            label.setFont(fontText);
            label.setTextFill(nottinghamBlue);
        }
        txtCurrentUsername = new TextField();
        txtCurrentUsername.setPromptText("Input Current Username");
        HBox currentUsernameHBox = new HBox(10, currentUsername, txtCurrentUsername);
        currentUsernameHBox.setAlignment(Pos.BASELINE_CENTER);
        txtCurrentPassword = new PasswordField();
        txtCurrentPassword.setPromptText("Input Current Password");
        HBox currentPasswordHBox = new HBox(10, currentPassword, txtCurrentPassword);
        currentPasswordHBox.setAlignment(Pos.BASELINE_CENTER);
        txtConfirmPassword = new PasswordField();
        txtConfirmPassword.setPromptText("Confirm New Password");
        HBox confirmPasswordHBox = new HBox(10, confirmPassword, txtConfirmPassword);
        confirmPasswordHBox.setAlignment(Pos.BASELINE_CENTER);
        txtNewPassword = new TextField();
        txtNewPassword.setPromptText("Input New Password");
        HBox passwordHBox = new HBox(10, newPassword, txtNewPassword);
        passwordHBox.setAlignment(Pos.BASELINE_CENTER);
        Button btnModify = new Button("Make Changes");
        btnModify.setOnAction(e -> {
           boolean success = controller.onModifyClick(txtCurrentUsername.getText(), txtCurrentPassword.getText(),
                   txtNewPassword.getText(),txtConfirmPassword.getText());
            if(success) {
                warning.setText("");
                changeSuccessful.setText("Profile Details have been modified");
                txtCurrentUsername.setText("");
                txtCurrentPassword.setText("");
                txtNewPassword.setText("");
                txtConfirmPassword.setText("");
            }else{
                warning.setText("Ensure correct details and new password! Unable to Modify Details");
                changeSuccessful.setText("");
            }
        });
        Button btnLogOut = new Button("Log Out");
        btnLogOut.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null) {
                eh.handle(new ViewChangeEvent(ViewManager.LOGIN));
            }

        });
        Button btnAdmin = new Button("Back to Admin Home Page");
        btnAdmin.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null) {
                eh.handle(new ViewChangeEvent(ViewManager.ADMINP));
            }
        });

        List<Button> buttons = List.of(btnLogOut, btnAdmin);
        for (Button button : buttons) {
            button.setFont(fontButton);
            button.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");
        }
        HBox menu = new HBox(20,btnLogOut, btnAdmin);
        menu.setAlignment(Pos.BASELINE_CENTER);

        btnModify.setFont( Font.font("Arial",20));
        btnModify.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");

        getChildren().addAll(image(),modifyPageLabel(), pageHeading(), menu,currentUsernameHBox, currentPasswordHBox,passwordHBox, confirmPasswordHBox, btnModify, changeSuccessful, warning);
        setPadding(new Insets(50,30,20,30));
        setAlignment(Pos.BASELINE_CENTER);
    }

    //function to get Admin Page Label
    public Label modifyPageLabel(){
        Font fontPageLabel = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25);
        Label adminPage = new Label("Modify Profile Dashboard");
        adminPage.setFont(fontPageLabel);
        adminPage.setTextFill(nottinghamBlue);
        return adminPage;
    }

    public Label pageHeading(){
        Font fontHeading = Font.font("Arial",FontWeight.BOLD,16);
        Label menteeMentorPairing = new Label("You must put in your current username and password to be able to change your password.");
        menteeMentorPairing.setFont(fontHeading);
        menteeMentorPairing.setTextFill(nottinghamRed);
        return menteeMentorPairing;
    }


    //function to get University of Nottingham logo
    public  ImageView image(){
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResource("/thisimage.png")).toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setSmooth(true);
        logoImageView.setFitWidth(350);
        logoImageView.setFitHeight(90);
        setStyle("-fx-background-color: #FDFBF9 ");

        return logoImageView;
    }

    @Override
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {//Question: What does this do?? Or what is it supposed to do
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {//Question:  What does this do?? Or what is it supposed to do
        onViewChange.set(eventHandler);
    }
}
