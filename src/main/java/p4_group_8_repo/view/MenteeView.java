package p4_group_8_repo.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import p4_group_8_repo.controller.MenteeController;

import java.util.List;
import java.util.Objects;

/**
 * The {@code MenteeView} provides the screen for a user after verifying that
 * the user is a Mentee after logging into the app.
 * It also implements {@code ManagedView} and it extends {@code VBox}
 */
public class MenteeView extends VBox implements ManagedView {
    private final MenteeController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Font fontText = Font.font("Arial", FontWeight.BOLD,18);
    private Color nottinghamBlue = Color.rgb(16,38,59);
    private Font fontButton = Font.font("Arial",  FontWeight.BOLD,14);

    public MenteeView(MenteeController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {

        setStyle("-fx-background-color: #FDFBF9");
        this.setSpacing(20);

        Label modifyProfile = new Label("To modify profile, click:");
        Label logOut = new Label("To log out, click:");
        Label allocatedMentor = new Label("To see your allocated mentor, click:");
        List<Label> labels = List.of(modifyProfile, logOut, allocatedMentor);
        for (Label label : labels) {
            label.setFont(fontText);
            label.setTextFill(nottinghamBlue);
        }

        Button btnModify = new Button("Modify Profile");
        Button btnLogOut = new Button("Log Out");
        Button btnAllocatedMentor = new Button("See Allocated Mentor");
        Button btnRequest = new Button("Request for a Mentor");
        List<Button> buttons = List.of(btnLogOut, btnModify, btnRequest, btnAllocatedMentor);
        for (Button button : buttons) {
            button.setFont(fontButton);
            button.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");
        }

        HBox modifyHbox = new HBox(10, modifyProfile, btnModify);
        modifyHbox.setAlignment(Pos.BASELINE_CENTER);
        HBox logOutHbox = new HBox(10, logOut, btnLogOut);
        logOutHbox.setAlignment(Pos.BASELINE_CENTER);
        HBox allocatedMentorHbox = new HBox(10, allocatedMentor, btnAllocatedMentor);
        allocatedMentorHbox.setAlignment(Pos.BASELINE_CENTER);

        btnLogOut.setOnAction(e -> {var eh = onViewChange.get();
            if (eh != null){
                eh.handle(new ViewChangeEvent(ViewManager.LOGIN));}
        });


        setPrefWidth(800);
        setPrefHeight(800);

        setMinWidth(800);
        setMinHeight(800);
        setMaxWidth(800);
        setMaxHeight(800);
        setPadding(new Insets(50,30,20,30));
        getChildren().addAll( image(), menteeDashboard(), menteePageLabel(),allocatedMentorHbox, modifyHbox, logOutHbox, btnRequest);
        setAlignment(Pos.BASELINE_CENTER);
    }

    //function to get Admin Page Label
    public Label menteeDashboard(){
        Font fontPageLabel = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25);
        Label mentorDashboard = new Label("Mentee Dashboard");
        mentorDashboard.setFont(fontPageLabel);
        mentorDashboard.setTextFill(nottinghamBlue);
        return mentorDashboard;
    }

    public Label menteePageLabel(){
        Font fontHeading = Font.font("Arial",FontWeight.BOLD,16);
        Label mentorPageLabel = new Label("Welcome to the Mentee Dashboard!!!");
        mentorPageLabel.setFont(fontHeading);
        mentorPageLabel.setTextFill(nottinghamBlue);
        return mentorPageLabel;
    }


    //function to get University of Nottingham logo
    public ImageView image(){
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResource("/thisimage.png")).toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setSmooth(true);
        logoImageView.setFitWidth(350);
        logoImageView.setFitHeight(90);
        setStyle("-fx-background-color: #FDFBF9 ");

        return logoImageView;
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
