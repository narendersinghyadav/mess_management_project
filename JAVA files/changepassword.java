import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class changepassword {
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
    private TextField new_password;

    @FXML
    private TextField confirm_password;

    @FXML
    void update(ActionEvent event) throws SQLException {
    	String pa=new_password.getText().toString().trim();
    	String pa1=confirm_password.getText().toString().trim();
    	if(pa.equals("")||pa1.equals("")) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!|");
    		alert.setContentText("enter password");
    		alert.showAndWait();
    	}
    	else if(!pa.equals(pa1)) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Unsuccessful Update!|");
    		alert.setContentText("password does not match");
    		alert.showAndWait();
    	}
    	else {
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        	PreparedStatement stmt=(PreparedStatement) con.prepareStatement("update login_details set password=? where student_id=?");
        	String pass=getMd5(pa);
        	stmt.setString(1, pass);
        	stmt.setString(2, login.sid1);
        	stmt.executeUpdate();
        	con.close();
        	Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Suuccessful Update!|");
    		alert.setContentText("Successfully changed password");
    		alert.showAndWait();
    	}
    }

}
