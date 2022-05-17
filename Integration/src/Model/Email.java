package Model;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Session;
import javax.mail.*;  
import javax.mail.Authenticator.*; 


public class Email {

	
	public void sendEmail(String title, String content) {
		try {
		
		//  final String user="integrationlion2022@gmail.com";
		// final String password="Lion2022"; 
		
		Properties properties = System.getProperties();
		

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true"); //TLS
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Settings.userEmail, Settings.userPassword);
            }
        });
		

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(user));  
	    message.setSubject(title);  
	    message.setText(content);

		MimeMessage message = new MimeMessage (session);
		message.setFrom(new InternetAddress(Settings.userEmail));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(Settings.userEmail));  
	    message.setSubject("Error");  
	    message.setText("Message to be sent of the ERROR");

	    Transport.send(message);
	    System.out.println("Message sent to: " + Settings.userEmail);
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
			
	}
		
	
	 
		

}
