import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class myprofile {

    @FXML
    private TextField phone;

    @FXML
    private Text student_id;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text hostel;

    @FXML
    private Text dept;

    @FXML
    private Text room;

    @FXML
    void update(ActionEvent event) throws SQLException {
    	String ph=phone.getText().toString().trim();
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
       	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("update student_details set phone_no=? where student_id=?");
       	stmt.setString(1, ph);
       	stmt.setString(2, login.sid1);
       	stmt.executeUpdate();
    	Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Updated successfully");
		alert.setContentText("Phone number successfully updated");
		alert.showAndWait();
    }
   @FXML
   void initialize() throws SQLException {
	   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
   	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("select * from student_details where student_id=?");
   	stmt.setString(1, login.sid1);
   	ResultSet rs=stmt.executeQuery();
   	byte[] bytes = null;
   	while(rs.next()) {
   		student_id.setText(rs.getString(1));
   		name.setText(rs.getString(2));
   		room.setText(rs.getString(3));
   		dept.setText(rs.getString(4));
   		phone.setText(rs.getString(5));
   		hostel.setText(rs.getString(6));
   		bytes=rs.getBytes(7);
   	}
   	Image img=new Image(new ByteArrayInputStream(bytes));
   	image.setImage(img);
   }

}
