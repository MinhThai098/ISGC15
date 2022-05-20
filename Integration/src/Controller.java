import java.util.ArrayList;

import Model.DollibarConnect;
import Model.Email;
import Model.FileManager;
import Model.Lead;
import Model.LogManager;
import Model.Settings;
import Model.Validator; 
public class Controller {

	
	
public static void main(String[] args) {
		
	
		LogManager logManager = new LogManager(); 
		Settings settings = new Settings(logManager);
		FileManager fileManager = new FileManager(logManager);
		DollibarConnect dolibarrConnect = new DollibarConnect(logManager); 
		Validator validator = new Validator(logManager); 
		Email email = new Email(logManager); 
	
		// Configure settings
		String path = "config.properties";
		settings.getSettings(path);
		
		
		// Gets last weeks leads
		ArrayList<Lead> lastWeekLeadList = dolibarrConnect.getLeads(); 


		// Gets leads from URL		
		// Gets new leads from URL		
		Boolean isSuccessful = fileManager.getUrlResponse();

		
		if(isSuccessful) {
	
		
			// Gets the new leads from XML file
			ArrayList<Lead> leadList = fileManager.getLeadsFromXML();

			System.out.println(leadList.size());
			System.out.println(lastWeekLeadList.size());

			
			// Compares new list to last weeks list
			 leadList = validator.compareLeadLists(lastWeekLeadList, leadList); 

			 // If theres no changes to leadlist
			 if(leadList.size() == 0) {
				 // send email
				 	email.sendEmail("Integration Lion error", "No new leads from webscrapers this week. Check the latest log file for more info");
				 
			 }else {
					// Verifies leadList and saves it to verrifiedList
					
				 // Verifies leadList
					ArrayList<Lead> verrifiedList = validator.validateLeads(leadList); 
				
					
					if(verrifiedList.size() != 0) {
							// Imports the new verrified leads to dolibarr
							dolibarrConnect.importLeads(verrifiedList);

				// 	If new verrified list size is == 0		
				} else {
					email.sendEmail("", "");

				}

			 }

				dolibarrConnect.removeLead();

			 dolibarrConnect.removeLead();

		// If response from URL does not work
		}else {
			
			 //send e-mail
				email.sendEmail("Integration Lion error", "Could not download files from webscrapers' URL. Check the latest log file for more info");
		}
		

				
		
		
	
	}
	
