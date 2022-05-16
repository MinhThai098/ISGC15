import java.util.ArrayList;

import Model.DollibarConnect;
import Model.FileManager;
import Model.Lead;
import Model.Settings;
import Model.Validator; 
public class Controller {

	
	
	
	public static void main(String[] args) {
		
		Settings settings = new Settings();
		FileManager fileManager = new FileManager();
		DollibarConnect dolibarrConnect = new DollibarConnect (settings); 
		Validator validator = new Validator(); 
	
		
		
		// Configure settings
		String path = "config.properties";
		settings.getSettings(path);
		
		

		// Gets last saved leads from XML 
		ArrayList<Lead> lastWeekLeadList = fileManager.getLeadsFromXML();

		// Gets leads from URL		
		Boolean isSuccessful = fileManager.getUrlResponse();


		
		if(isSuccessful) {
	
			
			ArrayList<Lead> leadList = fileManager.getLeadsFromXML();

			 leadList = validator.compareLeadLists(lastWeekLeadList, leadList); 
			
			 if(leadList.size() == 0) {
				 // send email
				 
			 }else {
					// Verifies leadList and saves it to verrifiedList
					ArrayList<Lead> verrifiedList = validator.validateLeads(leadList); 
					if(verrifiedList.size() != 0) {
							dolibarrConnect.importLeads(verrifiedList);
						}
			 }

			 
			 
			
			
		
			
			
			
			

		}else {
			
			// send e-mail
		}
		

				
		
		
	
	}
	
	

}
