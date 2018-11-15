import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sun.javafx.tk.FontLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class login {
	public static String balance;
 	public static boolean foo=false;
	public static String sid1;
	public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            byte[] messageDigest = md.digest(input.getBytes());
 
            BigInteger no = new BigInteger(1, messageDigest);
 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private TextField student_id;
	
    @FXML
    private PasswordField password;
    @FXML
    private Text error;


    @FXML
    void forget_password(MouseEvent event) {
    	try {
    		Stage s=new Stage();
    		//p.setLayoutY(500);
			Pane p=(Pane)FXMLLoader.load(login.class.getResource("reset.fxml"));
			s.setTitle("Reset Window");
			s.setScene(new Scene(p));
			s.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void login_button(ActionEvent event) {
    	final String pass=password.getText().toString();
    	final String id=student_id.getText().toString();
    	sid1=id;
    	
    	if(pass.equals("admin")&& id.equals("admin")) {
    		foo=true;
    		Stage s =new Stage();
			Pane p;
			try {
				p = (Pane)FXMLLoader.load(login.class.getResource("profile.fxml"));
				s.setTitle("Profile");
				s.setScene(new Scene(p));
				s.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	else if(pass.isEmpty()||id.isEmpty()) {
    		error.setText("must fill id and password");
    	}
    	else {
    		foo=false;
    	String pas =getMd5(pass);
    	try {
    		boolean flag=false;
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    		Statement stmt=(Statement) con.createStatement();
    		ResultSet rs=stmt.executeQuery("select * from login_details");
    		while(rs.next()) {
    			if( id.equals(rs.getString(1))) {
    			if(rs.getString(2).equals(pas)) {
    				System.out.println("successful");
    				flag=true;
    				Stage s =new Stage();
    				Pane p;
					try {
						PreparedStatement stmt1=(PreparedStatement) con.prepareStatement("select * from student_details where student_id=?");
						stmt1.setString(1, id);
						ResultSet r=stmt1.executeQuery();
						if(!r.next()) {
							p=(Pane)FXMLLoader.load(login.class.getResource("firstprofile.fxml"));
							s.setTitle("details");
							s.setScene(new Scene(p));
							s.show();
						}
						else {
						p = (Pane)FXMLLoader.load(login.class.getResource("profile.fxml"));
						s.setTitle("Profile");
	    				s.setScene(new Scene(p));
	    				s.show();
						}
	    				balance=rs.getString(3);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				//System.exit(0);
    			}
    		}
    		}
    		if(flag==false) {
    		System.out.println("Unsuccessful");
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Login!!");
    		alert.setHeaderText("Invalid Login");
    		alert.setContentText("Please check student id and password");
    		alert.showAndWait();
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	}
    }

}
