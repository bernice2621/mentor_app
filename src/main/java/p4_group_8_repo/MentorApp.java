package p4_group_8_repo;

import javafx.application.Application;
import javafx.stage.Stage;
import p4_group_8_repo.controller.AdminController;
import p4_group_8_repo.controller.LoginController;
import p4_group_8_repo.controller.MenteeController;
import p4_group_8_repo.controller.MentorController;
import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.RepositoryFactory;
import p4_group_8_repo.model.user.User;
import p4_group_8_repo.view.*;

import java.io.IOException;

/**
 * Main class for the entire mentor app. Remember to run the application using Gradle's {@code run}
 * task rather than running the app through IntelliJ.
 */
public class MentorApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  private Repository<User, String> loadMockData() throws IOException {
    RepositoryFactory factory = new RepositoryFactory();
    return factory.userHashMapRepository("/MOCK_DATA.csv");
  }

  private LoginView createLoginView(Repository<User, String> repo) {
    LoginController controller = new LoginController(repo);
    return new LoginView(controller);
  }

  private AdminView createAdminView(Repository<User, String> repo) {
    AdminController controller = new AdminController(repo);
    return new AdminView(controller);
  }

  private AdminProfileView createAdminProfileView(Repository<User, String> repo) {
    AdminController controller = new AdminController(repo);
    return new AdminProfileView(controller);
  }

  private MentorView createMentorView(Repository<User, String> repo) {
    MentorController controller = new MentorController(repo);
    return new MentorView(controller);
  }

  private MenteeView createMenteeView(Repository<User, String> repo) {
    MenteeController controller = new MenteeController(repo);
    return new MenteeView(controller);
  }


  @Override
  public void start(Stage stage) throws Exception {
    Repository<User, String> mockData = loadMockData();

    ViewManager vm = new ViewManager(stage);

    vm.addView(ViewManager.LOGIN, createLoginView(mockData));
    vm.addView(ViewManager.ADMINP, createAdminView(mockData));
    vm.addView(ViewManager.ADPROFILE, createAdminProfileView(mockData));
    vm.addView(ViewManager.MENTORP, createMentorView(mockData));
    vm.addView(ViewManager.MENTEEP, createMenteeView(mockData));

    vm.setStageView(ViewManager.LOGIN);
    stage.setTitle("Mentor App");
    stage.show();
  }
}

