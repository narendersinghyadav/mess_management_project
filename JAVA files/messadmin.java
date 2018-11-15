import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class messadmin {

    @FXML
    private TextField id;

    @FXML
    private TextField amount;

    @FXML
    private Button btn;
    @FXML
    void addnewstudent(ActionEvent event) throws IOException {
    	Stage s=new Stage();
    	Pane p =(Pane)FXMLLoader.load(messadmin.class.getResource("addstudentdetails.fxml"));
    	s.setTitle("New User");
    	s.setScene(new Scene(p));
    	s.show();

    }


    @FXML
    void addamount(ActionEvent event) throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("select Balance from login_details where student_id=?");
    	String sid=id.getText().toString();
    	stmt.setString(1,sid);
    	ResultSet rs=stmt.executeQuery();
    	int amt=0;
    	if(rs.next()) {
    		amt=rs.getInt(1);
    	PreparedStatement smt1=(PreparedStatement)con.prepareStatement("update login_details set Balance=? where student_id =?");
    	amt=amt+Integer.parseInt(amount.getText().toString());
    	smt1.setLong(1,amt);
    	smt1.setString(2, sid);
    	smt1.executeUpdate();
    	con.close();
    	btn.setDisable(true);
    }
    	else {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!!");
    		alert.setHeaderText("Invalid  details");
    		alert.setContentText("Please check student id");
    		alert.showAndWait();
    	}
    	}

}
