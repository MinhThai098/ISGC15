import java.util.ArrayList;

import Model.Clock;
import Model.DollibarConnect;
import Model.Email;
import Model.FileManager;
import Model.Lead;
import Model.LogManager;
import Model.Settings;
import Model.Validator;

public class ControllerMain {


	
	
	public static void main(String[] args) {
	

		LogManager logManager = new LogManager(); 
		logManager.logInfo("Starting program");

		Settings settings = new Settings(logManager);
		FileManager fileManager = new FileManager(logManager);
		DollibarConnect dolibarrConnect = new DollibarConnect(logManager); 
		Validator validator = new Validator(logManager); 
		Clock clock = new Clock();
		Email email = new Email(logManager); 

		
		String path = "config.properties";
		settings.getSettings(path);

		ArrayList<Lead> lastWeekLeadList = dolibarrConnect.getLeads(); 

	
		
		Boolean isSuccessful = false;
		
		// while loop to ensure it tries to get XML document from HTTP URL more than once.
		while (!isSuccessful){
			
		
			logManager.logInfo("Waiting...");
			clock.checkTime();
			logManager.logInfo("Initiating task");


			isSuccessful = fileManager.getUrlResponse();
			
			if (!isSuccessful) {
				//send e-mail
				email.sendEmail("Integration Lion error", 
						"Could not download files from webscrapers' URL. Check the latest log file for more info", 
						Settings.customerEmail);
				
				email.sendEmail("Integration Lion error", 
						"Could not download files from webscrapers' URL. Check the latest log file for more info", 
						Settings.webscraperEmail);
			}
			
			
	//		ArrayList<Lead> leadList = fileManager.getLeadsFromXML();
			ArrayList<Lead> leadList = fileManager.leadTest(); 


			ArrayList<Lead> verrifiedList = validator.validateLeads(leadList); 
			ArrayList<Lead> uniqueLeads = validator.compareLeadLists(lastWeekLeadList, verrifiedList); 

			
			if(uniqueLeads.size() != 0) {
		
				
				if(verrifiedList.size() != 0) {
				
					dolibarrConnect.importLeads(uniqueLeads);
					dolibarrConnect.removeLeads();
					isSuccessful = true; 
	
				} else {
					
					isSuccessful = false; 

					logManager.logInfo("No lead passed through validation"); 
					
					email.sendEmail("Integration Lion error", 
							"No lead passed through validation. Check the latest log file for more info", 
							Settings.customerEmail);
					
					email.sendEmail("Integration Lion error", 
							"No lead passed through validation. Check the latest log file for more info",
							Settings.webscraperEmail);
				
				}
					
			} else {
				isSuccessful = false; 
				logManager.logInfo("No new leads were unique"); 

				email.sendEmail("Integration Lion error", 
						"No new leads were unique. Check the latest log file for more info", 
						Settings.customerEmail);
				email.sendEmail("Integration Lion error", 
						"No new leads were unique. Check the latest log file for more info", 
						Settings.webscraperEmail);

			}
			
			System.out.println(isSuccessful);
			Settings.getLeadTime++;
		}
		
		logManager.logInfo("Closing program");

		
		
	
	
	}
	
	
	
	
}
