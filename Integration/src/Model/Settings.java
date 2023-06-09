package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Settings {
	public static int getLeadTime;
	public static int intervalHour;
	public static int intervalMinute;
	public static int checkEveryHour;
	public static String webscraperEmail;
	public static String customerEmail;
	public static String httpURL;
	public static String oauth2;
	public static String dbName;
	public static String dbPassword;
	public static String userEmail;
	public static String userPassword;
	public static String smtp;
	public static String port;
	public static String emailPort;
	public static java.sql.Date currentDate;
	public static String currentDate2; 
	public static int day; 
	
	private LogManager logManager;
	
	
	
	public Settings(LogManager logManager) {
		
		
		this.logManager = logManager; 
	}

	
	
	
	/** Called upon to read the properties of a config file and add its properties to variables
	 * @param path The path of a file
	 */
	 public void getSettings(String path) {
		 // Reads file from path
		 try(InputStream input = new FileInputStream(path)){
			 Properties properties = new Properties();
			 
			 properties.load(input);
			 
			 // checks if the loaded content is empty or not
			 if (input == null) {
				 logManager.logError("Error Could not load settings");
				 
			 } else {
				 
			 }
			 // loads variables from the properties
			 getLeadTime =			Integer.parseInt(properties.getProperty("getLeadTime"));
			 intervalHour =			Integer.parseInt(properties.getProperty("intervalHour"));
			 intervalMinute = 		Integer.parseInt(properties.getProperty("intervalMinute"));
			 checkEveryHour = 		Integer.parseInt(properties.getProperty("checkEveryHour"));
			 webscraperEmail =		properties.getProperty("webscraperEmail");
			 customerEmail = 		properties.getProperty("customerEmail");
			 httpURL = 				properties.getProperty("httpURL");
			 oauth2 = 				properties.getProperty("oauth2");
			 dbName =				properties.getProperty("dbName");
			 dbPassword = 			properties.getProperty("dbPassword");
			 userEmail =			properties.getProperty("userEmail");
			 userPassword =			properties.getProperty("userPassword");
			 smtp = 				properties.getProperty("smtp");
			 port = 				properties.getProperty("port");
			 emailPort = 			properties.getProperty("emailPort");
			 day = 	Integer.parseInt(properties.getProperty("day")); 
			 
			 Date today = new Date();
			 currentDate = new java.sql.Date(today.getTime());
			 currentDate2 = currentDate.toString();
			 System.out.println(currentDate2);
			 logManager.logInfo("Success reading Settings file");
			 
		 } catch (IOException e) {
			 e.printStackTrace();
			 logManager.logError("Error Could not read settings file");
		 }

	 }
	 


	

}
