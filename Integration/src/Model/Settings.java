package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Settings {
	public String webscraperEmail;
	public String customerEmail;
	public String httpURL;
	public String oauth2;
	public String dbName;
	public String dbPassword;
	public String userEmail;
	public String userPassword;
	
	private LogManager logManager;
	
	/** Called upon to read the properties of a config file and add its properties to variables
	 * @param path The path of a file
	 */
	 public void getSettings(String path) {
	 
		 try(InputStream input = new FileInputStream(path)){
			 Properties properties = new Properties();
			 
			 properties.load(input);
			 
			 if (input == null) {
				 logManager.logError("Error Could not load settings");
				 
			 }
			 
			 webscraperEmail =		properties.getProperty("webscraperEmail");
			 customerEmail = 		properties.getProperty("customerEmail");
			 httpURL = 				properties.getProperty("httpURL");
			 oauth2 = 				properties.getProperty("oauth2");
			 dbName =				properties.getProperty("dbName");
			 dbPassword = 			properties.getProperty("dbPassword");
			 userEmail =			properties.getProperty("userEmail");
			 userPassword =			properties.getProperty("userPassword");
			 
			 
			 logManager.logInfo("Success reading Settings file");
		 } catch (IOException e) {
			 e.printStackTrace();
			 logManager.logError("Error Could not read settings file");
		 }

	 }
	 


	

}
