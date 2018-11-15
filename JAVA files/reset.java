import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
	import javafx.scene.control.TextField;
	public class reset {
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
	    void reset_passw(ActionEvent event) {
	    	String email=student_id.getText().toString();
	    	email=email+"@mnit.ac.in";
	    	String username="messmnit123@gmail.com";
	    	String password="";
	    	String subject="Default password";
	    	String pass=getPassword();
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
				message.setContent("Your new password is"+pass,"text/html");
				/*Transport tr=session.getTransport("smtp");
				tr.connect("smtp.gmail.com",587,username,password);
				message.saveChanges();
				tr.sendMessage(message, );*/
				Transport.send(message);
				System.out.println("message successfully sent");
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		private String getPassword() {
			// TODO Auto-generated method stub
			Random r=new Random();
			long in=(long) Double.doubleToLongBits(Math.random());
			int n=1000000+r.nextInt(999999);
			String temp=Long.toHexString(n);
			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				//Statement stmt=(Statement) con.createStatement();
				String md=getMd5(temp);
				PreparedStatement stmt=(PreparedStatement) con.prepareStatement("update login_details set password=? where student_id=?");
				String em=student_id.getText().toString();
				stmt.setString(1,md);
				stmt.setString(2, em);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			
			return temp;
		}

	}
