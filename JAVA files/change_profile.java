import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class change_profile {

	private String id="";
    @FXML
    private Text edit;
    
    @FXML
    private TextField name;

    @FXML
    private TextField room_no;

    @FXML
    private Text text;

    @FXML
    private TextField mobile_no;

    @FXML
    private ChoiceBox<String> dept_name;

    @FXML
    private ChoiceBox<String> hostel_no;
    private String filename="";
    ObservableList<String> list=FXCollections.observableArrayList("CSE","ME","META","EE","ECE","CIVIL","CHEMICAL");
	ObservableList<String> list1=FXCollections.observableArrayList("1","2","3");
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
    void submit(ActionEvent event) throws SQLException {
    	String nam=name.getText().toString().trim();
    	String room=room_no.getText().toString().trim();
    	String hostel=hostel_no.getValue().toString().trim();
    	String dept=dept_name.getValue().toString().trim();
    	String mob=mobile_no.getText().toString().trim();
    	File imgfile=new File(filename);
    	try {
			FileInputStream fin = new FileInputStream(imgfile);
    	if(nam.equals("")||room.equals("")||hostel.equals("")||dept.equals("")||mob.equals("")) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!!");
    		alert.setContentText("All fields are manadatory");
    		alert.showAndWait();
    	}
    	else {
    		Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				PreparedStatement stmt=(PreparedStatement) con.prepareStatement("update student_details set name=?,room_no=?,dept_name=?,phone_no=?,hostel_no=?,photo=? where student_id=?");
	        	stmt.setString(1, nam);
	        	stmt.setString(2, room);
	        	stmt.setString(3, dept);
	        	stmt.setString(4,mob);
	        	stmt.setString(5, hostel);
	        	stmt.setBinaryStream(6,(InputStream)fin,(int)imgfile.length());
	        	stmt.setString(7,id);
	        	stmt.executeUpdate();
	        	stmt.close();
	        	con.close();
	        	Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Successfully Updated!");
	    		alert.setContentText("Login Again");
	    		alert.showAndWait();
			}
    			}catch (SQLException e) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("Unsuccessful Update!!");
    	    		alert.setContentText("All fields are manadatory");
    	    		alert.showAndWait();
			}catch (FileNotFoundException e) {
				Connection con;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				PreparedStatement stmt=(PreparedStatement) con.prepareStatement("update student_details set name=?,room_no=?,dept_name=?,phone_no=?,hostel_no=? where student_id=?");
	        	stmt.setString(1, nam);
	        	stmt.setString(2, room);
	        	stmt.setString(3, dept);
	        	stmt.setString(4,mob);
	        	stmt.setString(5, hostel);
	        	stmt.setString(6,id);
	        	stmt.executeUpdate();
	        	stmt.close();
	        	con.close();
	        	Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Successfully Updated!");
	    		alert.setContentText("Login Again");
	    		alert.showAndWait();
				}
    }
    @FXML
    void initialize() throws SQLException {
    	dept_name.setItems(list);
    	hostel_no.setItems(list1);
    	String h="",n="",m="",r="",d="";
    	id=(String) profile.id;
    	edit.setText("Edit details of "+id);
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    	PreparedStatement stmt=(PreparedStatement)con.prepareStatement("select name,room_no,dept_name,phone_no,hostel_no from student_details where student_id =?");
    	stmt.setString(1, id);
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next()) {
    		n=rs.getString(1);
    		r=rs.getString(2);
    		d=rs.getString(3);
    		m=rs.getString(4);
    		h=rs.getString(5);
    	}
    	name.setText(n);
    	room_no.setText(r);
    	dept_name.setValue(d);
		mobile_no.setText(m);
		hostel_no.setValue(h);
    }
}
