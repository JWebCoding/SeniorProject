import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginController=new LoginController();
        loginController.openLogin();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}