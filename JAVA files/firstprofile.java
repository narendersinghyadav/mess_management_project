import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class firstprofile {
	ObservableList<String> list=FXCollections.observableArrayList("CSE","ME","META","EE","ECE","CIVIL","CHEMICAL");
	ObservableList<String> list1=FXCollections.observableArrayList("1","2","3");

    @FXML
    private TextField name;
    
    @FXML
    private Text text;
    
    @FXML
    private TextField room_no;


    @FXML
    private ChoiceBox<String> hostel_no;

    @FXML
    private ChoiceBox<String> dept_name;

    @FXML
    private TextField mobile_no;
    private String filename="";
    @FXML
    private void initialize() {
    	dept_name.setItems(list);
    	hostel_no.setItems(list1);
    	dept_name.setValue("CSE");
    	hostel_no.setValue("1");
    }
    @FXML
    void addpicture(ActionEvent event) {
    	FileChooser.ExtensionFilter imageFilter= new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
    	FileChooser filechooser =new FileChooser();
    	filechooser.getExtensionFilters().add(imageFilter);
    	Stage s=new Stage();
    	filechooser.setTitle("Upload your pic");
    	File selectedfile=filechooser.showOpenDialog(s);
    	filename=selectedfile.getPath();
    	text.setText(filename);
    	}

    @FXML
    void submit(ActionEvent event) {
    	String nam=name.getText().toString().trim();
    	String room=room_no.getText().toString().trim();
    	String hostel=hostel_no.getValue().toString().trim();
    	String dept=dept_name.getValue().toString().trim();
    	String mob=mobile_no.getText().toString().trim();
    	File imgfile=new File(filename);
    	try {
			FileInputStream fin = new FileInputStream(imgfile);
    	if(nam.equals("")||room.equals("")||hostel.equals("")||dept.equals("")||mob.equals("")||filename.equals("")) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!!");
    		alert.setContentText("All fields are manadatory");
    		alert.showAndWait();
    	}
    	else {
    		Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				PreparedStatement stmt=(PreparedStatement) con.prepareStatement("insert into student_details values(?,?,?,?,?,?,?)");
	        	stmt.setString(1,login.sid1);
	        	stmt.setString(2, nam);
	        	stmt.setString(3, room);
	        	stmt.setString(4, dept);
	        	stmt.setString(5,mob);
	        	stmt.setString(6, hostel);
	        	stmt.setBinaryStream(7,(InputStream)fin,(int)imgfile.length());
	        	stmt.executeUpdate();
	        	stmt.close();
	        	con.close();
	        	Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Successfully Updated!");
	    		alert.setContentText("Login Again");
	    		alert.showAndWait();
	    		System.exit(1);
			}
    			}catch (SQLException e) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("Unsuccessful Update!!");
    	    		alert.setContentText("All fields are manadatory");
    	    		alert.showAndWait();
			}catch (FileNotFoundException e) {
				Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Error");
	    		alert.setContentText("Please select pic also");
	    		alert.showAndWait();
				}
        	
    	}
  
    }

