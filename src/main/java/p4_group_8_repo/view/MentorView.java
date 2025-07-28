package p4_group_8_repo.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import p4_group_8_repo.controller.MentorController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The {@code MentorView} provides the screen for a user after verifying that
 * the user is a Mentor after logging into the app.
 * It also implements {@code ManagedView} and it extends {@code VBox}
 */
public class MentorView extends VBox implements ManagedView {
    private final MentorController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Font fontText = Font.font("Arial", FontWeight.BOLD,18);
    private Color nottinghamBlue = Color.rgb(16,38,59);
    private Color nottinghamRed = Color.rgb(185, 28, 46);
    private Font fontButton = Font.font("Arial",  FontWeight.BOLD,14);

    public MentorView(MentorController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {
        setStyle("-fx-background-color: #FDFBF9");
        this.setSpacing(20);

        Label startDateLabel = new Label("Set available start date:");
        Label endDateLabel = new Label("Set available end date: ");
        List<Label> labels = List.of(startDateLabel, endDateLabel);

        for (Label label : labels) {
            label.setFont(fontText);
            label.setTextFill(nottinghamBlue);
        }
        Button btnModify = new Button("Modify Profile");
        Button btnLogOut = new Button("Log Out");
        Button btnAllocatedMentee = new Button("See Allocated Mentee");
        Button btnSet = new Button("Set Dates");
        List<Button> buttons = List.of(btnLogOut, btnModify, btnSet, btnAllocatedMentee);
        for (Button button : buttons) {
            button.setFont(fontButton);
            button.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");
        }

        HBox otherFeatures = new HBox(10, btnModify, btnLogOut, btnAllocatedMentee);

        btnLogOut.setOnAction(e -> {var eh = onViewChange.get();
            if (eh != null){
                eh.handle(new ViewChangeEvent(ViewManager.LOGIN));}
        });


        DatePicker startDate = new DatePicker();
        startDate.setOnAction(e -> {
            LocalDate begin = startDate.getValue();
        });
        HBox startDateHbox = new HBox(10, startDateLabel, startDate);

        DatePicker endDate = new DatePicker();
        endDate.setOnAction(e -> {
            LocalDate end = endDate.getValue();
        });
        HBox endDateHbox = new HBox(10, endDateLabel, endDate);

        setPrefWidth(800);
        setPrefHeight(800);

        setMinWidth(800);
        setMinHeight(800);
        setMaxWidth(800);
        setMaxHeight(800);
        setPadding(new Insets(50,30,20,30));
        getChildren().addAll( image(), mentorDashboard(), otherFeatures, mentorPageLabel(), startDateHbox, endDateHbox, btnSet);

    }

    /** creates Admin Page Label */
    public Label mentorDashboard(){
        Font fontPageLabel = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25);
        Label mentorDashboard = new Label("Mentor Dashboard");
        mentorDashboard.setFont(fontPageLabel);
        mentorDashboard.setTextFill(nottinghamBlue);
        return mentorDashboard;
    }

    public Label mentorPageLabel(){
        Font fontHeading = Font.font("Arial",FontWeight.BOLD,16);
        Label mentorPageLabel = new Label("Welcome to the Mentor Dashboard!!!");
        mentorPageLabel.setFont(fontHeading);
        mentorPageLabel.setTextFill(nottinghamBlue);
        return mentorPageLabel;
    }


    /** puts University of Nottingham logo */
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
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {//Question: What does this do?? Or what is it supposed to do
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {//Question:  What does this do?? Or what is it supposed to do
        onViewChange.set(eventHandler);
    }
}
