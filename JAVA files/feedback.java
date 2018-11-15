import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class feedback {

    @FXML
    private TextArea feedbackarea;

    @FXML
    void submit(ActionEvent event) throws SQLException {
    	//System.out.println("fd");
    	String feed=feedbackarea.getText().toString().trim();
    	if(feed.equals("")) {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		//alert.setTitle("Unsuccessful feedback");
    		alert.setHeaderText("please provide some input");
    		//alert.setContentText("Please enter something!!");
    		alert.showAndWait();
    	}
    	else {
    	
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
    		PreparedStatement pre=(PreparedStatement)con.prepareStatement("insert into feedback_table values(null,?,?,?)");
    		String student_id=login.sid1;
    		pre.setString(1,student_id);
    		pre.setString(2,feed);
    		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    		    Date date = new Date();  
    		String dt=formatter.format(date);
    		pre.setString(3, dt);
    		pre.executeUpdate();
    		String email=student_id;
	    	email=email+"@mnit.ac.in";
	    	String username="messmnit123@gmail.com";
	    	String password="qwer@12345";
	    	String subject="Feedback Received";
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
				message.setContent("We have received your feedback.We will consider your feedback.</br>Thank you!","text/html");
				
				Transport.send(message);
				Alert alert=new Alert(AlertType.INFORMATION);
	    		//alert.setTitle("Successful feedback");
	    		alert.setContentText("Your feedback is successfully submitted");
	    		alert.showAndWait();
    	}catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
}
