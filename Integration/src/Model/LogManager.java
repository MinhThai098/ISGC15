package Model;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



// class for managing logging
public class LogManager {

	private FileHandler fileHandler; 
	private Logger logger; 
	
	public LogManager() {
		
		try {
			
			// gets todays date
			Date today = new Date();
			Date currentDate = new java.sql.Date(today.getTime());
			
			// creates a logger and connect logfile to it
			fileHandler = new FileHandler("log " + currentDate + ".log", true); 
			logger = Logger.getLogger("logger"); 
			logger.addHandler(fileHandler);
			
			
			// formattes from XML to simpler format
			SimpleFormatter formatter = new SimpleFormatter(); 
			fileHandler.setFormatter(formatter);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	// write an info message to log
	public void logInfo(String text) {
	
		logger.info(text);
		
	}
	
	// write an error message to log
	public void logError(String text) {
		
		logger.severe(text);
	}
	
}
