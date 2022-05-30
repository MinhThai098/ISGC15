package Model;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Session;
import javax.mail.*;  
import javax.mail.Authenticator.*; 


public class Email {
	
	private LogManager logManager;
	

	public Email(LogManager logManager) {
		
		
		this.logManager = logManager; 
	}
	
	public void sendEmail(String title, String content, String sendTo) {
		try {
		
		/*
		 * Setting properties for the email connection
		 * - Dns name of a server that accept a specific port
		 * - enables the use of starttls supported by the server
		 * - enables the authorization for the email being able to send from a third party
		 * */
			
			
		Properties properties = System.getProperties();

		 properties.put("mail.smtp.host", Settings.smtp);
         properties.put("mail.smtp.port", Settings.emailPort);
         properties.put("mail.smtp.starttls.enable", "true"); //TLS
         properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
         properties.put("mail.smtp.auth", "true");

		//creating a sessions to create the request and response between the client and server.
		//and authentication of which email address to use and the password for that address
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Settings.userEmail, Settings.userPassword);
            }
        });
		
		/*
		 * Using MimMessage to decide the information being sent with the email, such as (subject, the actual content)
		 * And also from which address it should be sent from and to.
		 */
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(Settings.userEmail));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
	    message.setSubject(title);  
	    message.setText(content);

	    Transport.send(message);
		logManager.logInfo("Message sent to: " + sendTo);
		}catch(Exception e) {
			
			e.printStackTrace();
		logManager.logError("Could not send the email");
			System.out.println(e.getMessage());
		}
			
	}
		
	
	 
		

}
