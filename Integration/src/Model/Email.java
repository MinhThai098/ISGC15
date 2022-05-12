package Model;

import java.util.Properties;

import javax.mail.Session;

public class Email {
	
	public void sendEmail(String title, String content) {
		
		
		Properties properties = System.getProperties();
		
		

		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true"); //TLS
		
		Session session = Session.getDefaultInstance(properties);
		
		
	}

}
