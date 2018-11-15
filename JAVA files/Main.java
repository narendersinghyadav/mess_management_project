import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) {
		launch();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane mainPane=(Pane)FXMLLoader.load(Main.class.getResource("loginpage.fxml"));
		primaryStage.setTitle("Mess management system");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
	}

}
