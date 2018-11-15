import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class profile {
	public static String id;
    @FXML
    private Button buton;

    @FXML
    private Button mp;
    
    @FXML
    private Button change_pass;

    @FXML
    private Button annon;
    
    @FXML
    private TextField search_field;
    
    @FXML
    private Button search_btn;

    @FXML
    void search(ActionEvent event) throws IOException, SQLException {
    	if(search_field.getText().toString().equals("")) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Empty search");
    		alert.setContentText("write something in search box");
    		alert.showAndWait();
    	}
    	else {
    		Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			id=search_field.getText().toString();
			PreparedStatement stmt=(PreparedStatement) con.prepareStatement("select student_id from student_details where student_id=?");
			stmt.setString(1,id);
			ResultSet rs=stmt.executeQuery();
			if(!rs.next()) {
				Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("Invalid Search");
	    		alert.setContentText("Again check student id");
	    		alert.showAndWait();
			}
			else {
    		Stage s=new Stage();
    		s.setTitle("Change Profile");
    		Pane p=FXMLLoader.load(profile.class.getResource("change_profile.fxml"));
    		s.setScene(new Scene(p));
    		s.show();
			}
    	}
    }
    
    
    @FXML
    void addanon(ActionEvent event) {
    	Stage s=new Stage();
    	Pane p;
		try {
			p = (Pane)FXMLLoader.load(profile.class.getResource("announcement.fxml"));
			s.setTitle("Announcement");
	    	s.setScene(new Scene(p));
	    	s.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void myprofile(ActionEvent event) throws IOException {
    	Stage s=new Stage();
    	Pane p =(Pane)FXMLLoader.load(profile.class.getResource("myprofile.fxml"));
    	s.setTitle("New User");
    	s.setScene(new Scene(p));
    	s.show();
    }
    @FXML
    void changepassword(ActionEvent event) throws IOException {
    	Stage s=new Stage();
    	Pane p =(Pane)FXMLLoader.load(profile.class.getResource("change_password.fxml"));
    	s.setTitle("New User");
    	s.setScene(new Scene(p));
    	s.show();
    }
    

    @FXML
    void initialize() throws SQLException {
    	if(login.foo==true) {
    		buton.setText("Add Students");
    		change_pass.setDisable(true);
    		mp.setDisable(true);
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
 		    Date date = new Date();  
 		    String dt=formatter.format(date);
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("select COUNT(student_id) from mess_allow where dates=?");
        	stmt.setString(1,dt);
        	String count = null;
        	ResultSet rs=stmt.executeQuery();
        	while(rs.next()) {
        		count=rs.getString(1);
        	}
        	Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Number of Students");
    		alert.setContentText("Today "+count+" student have coupon.So make food accordingly");
    		alert.showAndWait();
    	}
    	else {
    		annon.setDisable(true);
    		search_btn.setDisable(true);
    	}
    }

    @FXML
    void logout(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void mess_schedule(ActionEvent event) {
    	Stage s=new Stage();
    	try {
			Pane p=(Pane)FXMLLoader.load(profile.class.getResource("messschedule.fxml"));
			s.setTitle("Mess Schedule");
			s.setScene(new Scene(p));
			s.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void messbalance(ActionEvent event) {
    	if(login.foo==false) {
    	Stage s =new Stage();
    	try {
			Pane p=(Pane )FXMLLoader.load(profile.class.getResource("messbalance.fxml"));
			s.setTitle("Mess Balance");
			s.setScene(new Scene(p));
			s.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    	else {
    		Stage s=new Stage();
    		Pane p;
			try {
				p = (Pane)FXMLLoader.load(messbalance.class.getResource("messadmin.fxml"));
				s.setTitle("Mess Balance");
	    		s.setScene(new Scene(p));
	    		s.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    @FXML
    void add_feedback(ActionEvent event) throws IOException {
    		if(login.foo==true) {
    			Stage s=new Stage();
    			Pane p=(Pane)FXMLLoader.load(profile.class.getResource("feed_received.fxml"));
    			s.setTitle("Feedback");
    			s.setScene(new Scene(p));
    			s.show();
    			
    		}
    		else if(login.foo==false) {
    			Stage s=new Stage();
    			Pane p=(Pane)FXMLLoader.load(profile.class.getResource("feedback_student.fxml"));
    			s.setTitle("Feedback");
    			s.setScene(new Scene(p));
    			s.show();
    			
    		}
    }

}
