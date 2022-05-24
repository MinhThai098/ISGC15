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
		Settings settings = new Settings(logManager);
		FileManager fileManager = new FileManager(logManager);
		DollibarConnect dolibarrConnect = new DollibarConnect(logManager); 
		Validator validator = new Validator(logManager); 
		Email email = new Email(logManager); 
		Clock clock = new Clock();

		
		String path = "config.properties";
		settings.getSettings(path);
		
		ArrayList<Lead> lastWeekLeadList = dolibarrConnect.getLeads(); 

		
		Boolean isSuccessful = false;
		
		// while loop to ensure it tries to get XML document from HTTP URL more than once.
		while (!isSuccessful){
			clock.checkTime();
			
			isSuccessful = fileManager.getUrlResponse();
			
			if (!isSuccessful) {
				//send e-mail
			//	email.sendEmail("Integration Lion error", "Could not download files from webscrapers' URL. Check the latest log file for more info");
			}
			
			
		//	ArrayList<Lead> leadList = fileManager.getLeadsFromXML();
			ArrayList<Lead> leadList = fileManager.leadTest(); 
			ArrayList<Lead> uniqueLeads = validator.compareLeadLists(lastWeekLeadList, leadList); 

			
			if(uniqueLeads.size() != 0) {
		
				
				ArrayList<Lead> verrifiedList = validator.validateLeads(leadList); 
				
				if(verrifiedList.size() != 0) {
				
					dolibarrConnect.importLeads(verrifiedList);
	
				} else {
				//	email.sendEmail("", "");
				}
					
			} else {
				isSuccessful = false; 
			//	email.sendEmail("", "");

			}
			
			System.out.println(isSuccessful);
			Settings.getLeadTime++;
		}
		
		
		
		
		dolibarrConnect.removeLead();
		
	
	
	}
	
	
	
	
}
