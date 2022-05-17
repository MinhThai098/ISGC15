package Model;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.*;  
import javax.mail.internet.*;  

public class Email {

	
	public void sendEmail(String title, String content) {
		
		  final String user="integrationlion2022@gmail.com";
		  final String password="Lion2022"; 
		
		Properties properties = System.getProperties();
		

		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true"); //TLS
		
		Session session = Session.getDefaultInstance(properties);
		
		
	}
	
	public void createmessage() {
		
		//Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.auth", "true");  
		     
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("javatpoint");  
		     message.setText("This is simple program of sending email using JavaMail API");  
		       
		    //send the message  
		     Transport.send(message);  
		  
		     System.out.println("message sent successfully...");  
		   
		     } catch (MessagingException e) {e.printStackTrace();}  
		 }  
		

}
