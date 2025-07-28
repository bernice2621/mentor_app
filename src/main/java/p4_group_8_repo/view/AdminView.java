package p4_group_8_repo.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import p4_group_8_repo.controller.AdminController;
import p4_group_8_repo.model.PairedMenteeMentor;
import p4_group_8_repo.model.user.Mentor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
/**
 * The {@code AdminView} provides the screen for a user after verifying that
 * the user is an Administrator after logging into the app.
 * It also implements {@code ManagedView} and it extends {@code VBox}
 */
public class AdminView extends VBox implements ManagedView {
    private final AdminController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Font fontText,fontHeading;
    private Color nottinghamBlue, nottinghamRed ;
    private ComboBox<String> menteeComboBox ;
    private ComboBox<Mentor> mentorComboBox;
    private Label selectedMenteeLabel, selectedMentorLabel, notPaired;
    private Button btnMakePair;
    private final ObservableList<PairedMenteeMentor> pairObservableList = FXCollections.observableArrayList();

    /** constructor to link the AdminController to the AdminView class*/
    public AdminView(AdminController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    /** builds the view*/
    private void buildView() {
        this.setSpacing(10);
        initialiseFontsAndColours();
        Label menteeMentorPairing = createBlueHeading("Mentee-Mentor Pairing");
        Label allocationReportsLabel = createBlueHeading("Allocation Reports");
        Label menteeListLabel = createBlueTextLabel("Select a mentee to pair");
        Label information = createRedTextLabel("Note: You must select a mentee and a mentor to pair");
        Label mentorListLabel = createBlueTextLabel("Select a mentor to pair");
        selectedMenteeLabel = createRedTextLabel("No Mentee Selected");
        selectedMentorLabel = createRedTextLabel("No Mentor Selected");
        HBox selectedMenteeMentor = new HBox(15, selectedMenteeLabel, selectedMentorLabel);
        HBox selectMentee = new HBox(15, menteeListLabel, menteeComboBox());
        HBox selectMentor = new HBox(15, mentorListLabel, mentorComboBox());
        btnMakePair = createMakePair();
        HBox otherFeatures = new HBox(10, createModify(), createLogOut());
        sizeSpecifications();
        getChildren().addAll( image(), adminLabel(), otherFeatures, menteeMentorPairing,information, selectMentee,
                selectMentor, selectedMenteeMentor,notPaired, btnMakePair, allocationReportsLabel,createTableView());
        setPadding(new Insets(50,30,20,30));
    }

    /** sets view size specifications*/
    private void sizeSpecifications(){
        setPrefWidth(800);
        setPrefHeight(800);
        setMinWidth(800);
        setMinHeight(800);
        setMaxWidth(800);
        setMaxHeight(800);
    }

    /** initialises fonts and colours */
    private void initialiseFontsAndColours() {
        fontText = Font.font("Arial",  FontWeight.BOLD,14);
        fontHeading = Font.font("Arial",FontWeight.BOLD,16);
        nottinghamBlue = Color.rgb(16,38,59);
        nottinghamRed = Color.rgb(185, 28, 46);
    }

    /**creates ComboBox containing available unpaired mentors*/
    private ComboBox<Mentor> mentorComboBox(){
        List<Mentor> mentorList = controller.mentorList(); // Get the list of mentors
        mentorComboBox = new ComboBox<>();
        mentorComboBox.getItems().addAll(mentorList);
        mentorComboBox.setPromptText("Select mentor");
        mentorComboBox.setStyle( "-fx-background-color: #CFD4D8;" +  "-fx-text-fill: #10263B;" );

        mentorComboBox.setOnAction(e -> selectedMentorLabel.setText("Selected mentee: "+ mentorComboBox.getValue()));
        return mentorComboBox;
    }

    /** creates ComboBox containing unpaired mentees */
    private ComboBox<String> menteeComboBox(){
        List<String> menteeList = controller.menteeList();
        menteeComboBox = new ComboBox<>();
        menteeComboBox.getItems().addAll(menteeList);
        menteeComboBox.setPromptText("Select mentee");
        menteeComboBox.setStyle("-fx-background-color: #CFD4D8;" + "-fx-text-fill: #10263B;");
        menteeComboBox.setOnAction(e -> selectedMenteeLabel.setText("Selected mentee: "+ menteeComboBox.getValue()));
        return menteeComboBox;
    }

    /** creates regular text label that uses the University of Nottingham Jubilee Red colour and a specific font*/
    private Label createRedTextLabel(String m) {
        Label selectedLabelName = new Label(m);
        selectedLabelName.setFont(fontText);
        selectedLabelName.setTextFill(nottinghamRed);
        return selectedLabelName;
    }

    /**creates the table that contains the allocation reports
     * (mentee, mentor, available start and available end date)
     * @return the allocation reports table*/
    private TableView<PairedMenteeMentor> createTableView() {
        TableView<PairedMenteeMentor> tableView = new TableView<>(pairObservableList);

        TableColumn<PairedMenteeMentor, String> menteeColumn = new TableColumn<>("Mentee");
        menteeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().mentee()));

        TableColumn<PairedMenteeMentor, String> mentorColumn = new TableColumn<>("Mentor");
        mentorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().mentor().getUsername()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        TableColumn<PairedMenteeMentor, String> mentorStartAvailabilityColumn = new TableColumn<>("Mentor Start Availability Date");
        mentorStartAvailabilityColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().mentor().getStartAvailability().format(formatter)));

        TableColumn<PairedMenteeMentor, String> mentorEndAvailabilityColumn = new TableColumn<>("Mentor End Availability Date");
        mentorEndAvailabilityColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().mentor().getEndAvailability().format(formatter)));

        mentorStartAvailabilityColumn.setPrefWidth(200);
        mentorEndAvailabilityColumn.setPrefWidth(200);

        tableView.getColumns().add(menteeColumn);
        tableView.getColumns().add(mentorColumn);
        tableView.getColumns().add(mentorStartAvailabilityColumn);
        tableView.getColumns().add(mentorEndAvailabilityColumn);
        return tableView;
    }

    /** creates regular text label that uses the University of Nottingham Blue colour*/
    private Label createBlueTextLabel(String m) {
        Label ListLabel = new Label(m);
        ListLabel.setFont(fontText);
        ListLabel.setTextFill(nottinghamBlue);
        return ListLabel;
    }

    /** creates heading label that uses the University of Nottingham Blue colour and bold font*/
    private Label createBlueHeading(String m){
        Label heading = new Label(m);
        heading.setFont(fontHeading);
        heading.setTextFill(nottinghamBlue);
        return heading;
    }

    /** creates button to modify profile */
    private Button createModify() {
        Button btnModify = new Button("Modify Profile");
        btnModify.setFont(fontText);
        btnModify.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");

        btnModify.setOnAction(e -> {
            menteeComboBox.getSelectionModel().clearSelection();
            mentorComboBox.getSelectionModel().clearSelection();
            menteeComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
            mentorComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
            menteeComboBox.setPromptText("Select mentee");
            mentorComboBox.setPromptText("Select mentor");
            var eh = onViewChange.get();
            if (eh != null){
                eh.handle(new ViewChangeEvent(ViewManager.ADPROFILE));}
        });
        return btnModify;
    }

    /** creates button to log out */
    private Button createLogOut(){
        Button btnLogOut = new Button("Log Out");
        btnLogOut.setFont(fontText);
        btnLogOut.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");

        btnLogOut.setOnAction(e -> {
            menteeComboBox.getSelectionModel().clearSelection();
            mentorComboBox.getSelectionModel().clearSelection();
            menteeComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
            mentorComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
            menteeComboBox.setPromptText("Select mentee");
            mentorComboBox.setPromptText("Select mentor");
            var eh = onViewChange.get();
            if (eh != null){
                eh.handle(new ViewChangeEvent(ViewManager.LOGIN));}
        });
        return btnLogOut;
    }

    /** creates button to pair mentee and mentor,remove mentee and mentor from respective combo box
     * and add mentee, mentor and the available start date and available end date to the allocation table*/
    private Button createMakePair(){
        btnMakePair = new Button("Pair selected mentee with mentor");
        btnMakePair.setFont(fontText);
        btnMakePair.setStyle("-fx-background-color: #10263B; -fx-text-fill: white");
        notPaired = new Label();
        btnMakePair.setOnAction(e -> {
            if( mentorComboBox.getValue() != null && menteeComboBox.getValue() != null){
                pairObservableList.add(new PairedMenteeMentor(menteeComboBox.getValue(), mentorComboBox.getValue()));
                menteeComboBox.getItems().remove(menteeComboBox.getValue());
                mentorComboBox.getItems().remove(mentorComboBox.getValue());
                menteeComboBox.getSelectionModel().clearSelection();
                mentorComboBox.getSelectionModel().clearSelection();
                menteeComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
                mentorComboBox.setValue(null); // Ensures the ComboBox value is explicitly set to null
                menteeComboBox.setPromptText("Select mentee");
                mentorComboBox.setPromptText("Select mentor");
                notPaired.setText("");
            }else{
                notPaired.setText("Warning: Pair cannot be made: Ensure mentor and mentee are selected");
                notPaired.setFont(fontText);
                notPaired.setTextFill(nottinghamRed);
            }
        });
        return btnMakePair;
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

    /** creates Admin Page Label */
    public Label adminLabel(){
        Font fontAdminLabel = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25);
        Label adminPage = new Label("Mentor App Administrator Dashboard");
        adminPage.setFont(fontAdminLabel);
        adminPage.setTextFill(nottinghamBlue);
        return adminPage;
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






