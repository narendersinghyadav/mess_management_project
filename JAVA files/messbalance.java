import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class messbalance {

    @FXML
    private Text balance;
    @FXML
    void initialize() throws IOException {
    	balance.setText(login.balance);
    	
    }

}
