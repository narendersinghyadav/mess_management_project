
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class announcement_done {

    @FXML
    private TextArea annonuncement;

    @FXML
    void announce(ActionEvent event) throws SQLException {
    	String a=annonuncement.getText().toString();
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    	Statement stmt=(Statement) con.createStatement();
		ResultSet rs=stmt.executeQuery("select student_id from login_details");
		while(rs.next()) {
    	String email=rs.getString(1);
    	email=email+"@mnit.ac.in";
    	String username="messmnit123@gmail.com";
    	String password="qwer@12345";
    	String subject="Important Announcement about mess";
    	Properties props=System.getProperties();
    	props.put("mail.smtp.starttls.enable","true");
    	props.put("mail.smtp.host","smtp.gmail.com");
    	props.put("mail.smtp.port","587");
    	props.put("mail.smtp.auth","true");
    	props.put("mail.smtp.starttls.required","true");
    	Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
    			
    		}
    	});
    	MimeMessage message=new MimeMessage(session);
    	try {
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setContent(a+"</br>Thank you!","text/html");
			
			Transport.send(message);
			
	}catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Announcement done");
		alert.setContentText("Mail sent to each user");
		alert.showAndWait();
    }

}
