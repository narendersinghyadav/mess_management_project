import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class addstudentdetails {

    @FXML
    private TextField studid;

    @FXML
    private TextField pwd;

    @FXML
    private TextField bal;

    @FXML
    private Button addbutton;

    @FXML
    void addstudent(ActionEvent event) throws SQLException {
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("select Balance from login_details where student_id=?");
    	final String sid=studid.getText().toString();
    	stmt.setString(1,sid);
    	ResultSet rs=stmt.executeQuery();
    	if(!rs.next()) {
    		PreparedStatement stmt1=(PreparedStatement) con.prepareStatement("insert into login_details values(?,?,?)");
    		stmt1.setString(1, sid);
    		stmt1.setString(2, pwd.getText().toString());
    		stmt1.setLong(3, Integer.parseInt(bal.getText().toString()));
    		stmt1.executeUpdate();
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Successful Update!!");
    		alert.setHeaderText("Added");
    		alert.setContentText("This student added");
    		alert.showAndWait();
    	}
    	else {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!!");
    		alert.setHeaderText("Already exits");
    		alert.setContentText("this student already exists");
    		alert.showAndWait();
    	}

    }

}
