package Model;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.*;  
import javax.mail.internet.*;  

public class Email {

	
	public void sendEmail(String title, String content) {
		try {
		
		  final String user="integrationlion2022@gmail.com";
		  final String password="Lion2022"; 
		
		Properties properties = System.getProperties();
		

		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true"); //TLS
		
		Session session = Session.getInstance(properties);
		
		MimeMessage message = new MimeMessage (session);
		message.setFrom(new InternetAddress(user));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(user));  
	    message.setSubject("Error");  
	    message.setText("Message to be sent of the ERROR");
	    Transport.send(message);
	    System.out.println("Message sent to: " + user);
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
			
	}
		
	
	 
		

}
